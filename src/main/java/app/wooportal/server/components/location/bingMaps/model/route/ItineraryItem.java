
package app.wooportal.server.components.location.bingMaps.model.route;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryItem {

  private String compassDirection;
  private List<Detail> details;
  private String exit;
  private String iconType;
  private Instruction instruction;
  private Boolean isRealTimeTransit;
  private ManeuverPoint maneuverPoint;
  private Integer realTimeTransitDelay;
  private String sideOfStreet;
  private String tollZone;
  private String towardsRoadName;
  private String transitTerminus;
  private Integer travelDistance;
  private Integer travelDuration;
  private String travelMode;
  private List<Hint> hints;

}
