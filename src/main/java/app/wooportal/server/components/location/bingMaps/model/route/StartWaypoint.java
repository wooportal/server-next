
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
public class StartWaypoint {

  private String type;
  private List<Double> coordinates;
  private String description;
  private Boolean isVia;
  private String locationIdentifier;
  private Integer routePathIndex;

}
