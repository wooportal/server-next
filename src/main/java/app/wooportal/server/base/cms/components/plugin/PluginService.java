package app.wooportal.server.base.cms.components.plugin;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.components.menuItem.MenuItemService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class PluginService extends DataService<PluginEntity, PluginPredicateBuilder> {
  
  public static String articlesPlugin = "articles";
  public static String authorsPlugin = "authors";
  public static String calendarPlugin = "calendar";
  public static String contestsPlugin = "contests";
  public static String dealsPlugin = "deals";
  public static String eventsPlugin = "events";
  public static String formsPlugin = "forms";
  public static String guestarticlePlugin = "guestarticle";
  public static String mapPlugin = "map";
  public static String mediaPlugin = "media";
  public static String organisationsPlugin = "organisations";
  public static String reportsPlugin = "reports";
  public static String surveysPlugin = "surveys";

  public PluginService(
      DataRepository<PluginEntity> repo,
      PluginPredicateBuilder predicate,
      MenuItemService menuItemService) {
    super(repo, predicate);
   
    addService("menuItems", menuItemService);
  
  }
  
  public Boolean changeActivation(String pluginId, Boolean active) {
    var plugin = repo.findOne(
        singleQuery(predicate.withId(pluginId)).addGraph(graph("menuItems"))
    );
    
    if (plugin.isPresent()) {
      var newPlugin = ReflectionUtils.copy(plugin.get());
      newPlugin.setActive(active);
      
      if (active == null || !active) {
        newPlugin.setMenuItems(null);
      }

      save(newPlugin);
      return true;      
    }
    return false;
  }
  
  public PluginEntity getArticlesPlugin() {
    var plugin = getByKey(articlesPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + articlesPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getAuthorsPlugin() {
    var plugin = getByKey(authorsPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + authorsPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getCalendarPlugin() {
    var plugin = getByKey(calendarPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + calendarPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getContestsPlugin() {
    var plugin = getByKey(contestsPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + contestsPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getDealsPlugin() {
    var plugin = getByKey(dealsPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + dealsPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getEventsPlugin() {
    var plugin = getByKey(eventsPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + eventsPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getFormsPlugin() {
    var plugin = getByKey(formsPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + formsPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getGuestarticlePlugin() {
    var plugin = getByKey(guestarticlePlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + guestarticlePlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getMapPlugin() {
    var plugin = getByKey(mapPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + mapPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getMediaPlugin() {
    var plugin = getByKey(mediaPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + mediaPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getOrganisationsPlugin() {
    var plugin = getByKey(organisationsPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + organisationsPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getReportsPlugin() {
    var plugin = getByKey(reportsPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + reportsPlugin);
    }
    
    return plugin.get();
  }
  
  public PluginEntity getSurveysPlugin() {
    var plugin = getByKey(surveysPlugin);
    
    if (plugin.isEmpty()) {
      throw new RuntimeException("Include Plugin: " + surveysPlugin);
    }
    
    return plugin.get();
  }

  public Optional<PluginEntity> getByKey(String code) {
    return repo.findOne(singleQuery(predicate.withCode(code)));
  }

}