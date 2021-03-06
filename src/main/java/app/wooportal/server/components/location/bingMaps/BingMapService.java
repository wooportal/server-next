package app.wooportal.server.components.location.bingMaps;

import java.net.URI;
import java.util.List;
import javax.naming.ServiceUnavailableException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import app.wooportal.server.components.location.LocationConfiguration;
import app.wooportal.server.components.location.address.AddressEntity;
import app.wooportal.server.components.location.bingMaps.model.BingMapResult;
import app.wooportal.server.components.location.bingMaps.model.address.Address;
import app.wooportal.server.components.location.bingMaps.model.address.AddressResource;
import app.wooportal.server.components.location.bingMaps.model.address.AddressResourceSet;
import app.wooportal.server.components.location.bingMaps.model.address.BingMapAddressResult;
import app.wooportal.server.components.location.bingMaps.model.address.Point;
import app.wooportal.server.components.location.bingMaps.model.route.BingMapLocationResult;
import app.wooportal.server.components.location.bingMaps.model.route.RouteResource;
import app.wooportal.server.components.location.bingMaps.model.route.RouteResourceSet;
import app.wooportal.server.components.location.dto.Coordinate;
import app.wooportal.server.components.location.dto.LocationParam;
import app.wooportal.server.components.location.dto.TravelMode;
import app.wooportal.server.core.error.exception.NotFoundException;

@Service
public class BingMapService {

  private final LocationConfiguration config;

  private final WebClient geoLocationClient;

  public BingMapService(
      LocationConfiguration config) {
    this.config = config;
    this.geoLocationClient = WebClient.create();
  }

  public AddressEntity retrieveExternalAddress(AddressEntity newAddress)
      throws ServiceUnavailableException, NotFoundException {
    var result = geoLocationClient
        .method(HttpMethod.GET)
        .uri(createAddressUri(newAddress))
        .retrieve().bodyToMono(BingMapAddressResult.class).block();
    isValid(result);

    return transformResultToAddress(result);
  }

  private URI createAddressUri(AddressEntity newAddress) {
    return UriComponentsBuilder.fromUriString(config.getAddressUrl())
        .path(newAddress.getPostalCode()).path("/" + newAddress.getPlace())
        .path("/" + newAddress.getStreet() + " " + newAddress.getHouseNumber())
        .queryParam("key", config.getServiceSubscriptionKey()).build().encode().toUri();
  }

  private AddressEntity transformResultToAddress(BingMapAddressResult result) {
    for (AddressResourceSet resourceSet : result.getResourceSets()) {
      if (resourceSet.getEstimatedTotal() > 0) {
        for (AddressResource resource : resourceSet.getResources()) {
          if (resource.getConfidence().equals("High") || resource.getConfidence().equals("high")) {
            return createAddress(resource.getAddress(), resource.getPoint());
          }
        }
      }
    }
    throw new NotFoundException("Address not found");
  }

  private AddressEntity createAddress(Address address, Point point) {
    AddressEntity newAddress = new AddressEntity();

    newAddress.setPostalCode(address.getPostalCode());
    newAddress.setPlace(address.getLocality());
    newAddress.setHouseNumber(address.getHouseNumber());
    newAddress.setStreet(address.getStreet());
    newAddress.setLatitude(point.getLatitude());
    newAddress.setLongitude(point.getLongitude());

    return newAddress;
  }

  public RouteResource calculateRoute(LocationParam params) 
      throws ServiceUnavailableException, NotFoundException {
    try {
      BingMapLocationResult result = geoLocationClient
          .method(HttpMethod.GET)
          .uri(createRouteUri(params))
          .retrieve().bodyToMono(BingMapLocationResult.class).block();
      isValid(result);

      return extractRouteResult(result);
    } catch (WebClientResponseException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND
          && params.getTravelMode() == TravelMode.TRANSIT) {
        params.setTravelMode(TravelMode.DRIVING);
        return calculateRoute(params);
      }
      throw new ServiceUnavailableException(e.getMessage());
    }
  }

  private URI createRouteUri(LocationParam params) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(config.getRoutesUrl());

    addTravelMode(builder, params.getTravelMode());
    addStartPoint(builder, params.getStartPoint());
    addVia(builder, params.getVia());
    addRouteOptions(builder);
    addLocale(builder);
    
    addTargetPoint(builder, params.getVia(), params.getTargetPoint());
    addKey(builder);
    
    return builder.build().encode().toUri();
    
  }

  private void addTravelMode(UriComponentsBuilder builder, TravelMode travelMode) {
    if (travelMode != null && !travelMode.name().isEmpty()) {
      builder.path("/" + travelMode.name().toLowerCase());
    }
  }

  private void addStartPoint(UriComponentsBuilder builder, Coordinate startPoint) {
    builder.queryParam("wayPoint.0", startPoint.toString());
  }

  private void addVia(UriComponentsBuilder builder, List<Coordinate> via) {
    if (via != null && !via.isEmpty()) {
      for (int i = 0; i < via.size(); i++) {
        builder.queryParam("wayPoint." + (i + 1), via.get(i).toString());
      }
    }
  }
  
  private void addLocale(UriComponentsBuilder builder) {
    //TODO: check for correct local
    builder.queryParam("culture", "de");
  }
  
  /**
   * Adds the key.
   *
   * @param builder the builder
   */
  private void addKey(UriComponentsBuilder builder) {
    builder.queryParam("key", config.getServiceSubscriptionKey());
  }
  
  private void addRouteOptions(UriComponentsBuilder builder) {
    builder.queryParam("routeAttributes", "routePath");
  }
  
  private void addTargetPoint(
      UriComponentsBuilder builder, 
      List<Coordinate> via,
      Coordinate targetPoint) {
    if (via == null || via.isEmpty()) {
      builder.queryParam("wayPoint.1", targetPoint.toString());
    } else {
      builder.queryParam("wayPoint." + via.size(), targetPoint.toString()); 
    }
  }
  
  private RouteResource extractRouteResult(BingMapLocationResult result) {
    for (RouteResourceSet resourceSet : result.getResourceSets()) {
      if (resourceSet.getEstimatedTotal() > 0) {
        return resourceSet.getResources().get(0);
      }
    }
    throw new NotFoundException("Route not found");
  }

  private void isValid(BingMapResult result) throws ServiceUnavailableException {
    if (result.getStatusCode() != 200
        || !result.getAuthenticationResultCode().equals("ValidCredentials")) {
      throw new ServiceUnavailableException("External API is not available");
    }
  }


}
