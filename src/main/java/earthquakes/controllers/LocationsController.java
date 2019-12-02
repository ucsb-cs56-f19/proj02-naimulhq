package earthquakes.controllers;

import java.util.List;
import earthquakes.osm.Place;
import earthquakes.repositories.LocationRepository;
import earthquakes.geojson.FeatureCollection;
import earthquakes.searches.LocSearch;
import earthquakes.services.EarthquakeQueryService;
import earthquakes.searches.EqSearch;
import earthquakes.services.LocationQueryService;
import earthquakes.repositories.LocationRepository;
import earthquakes.entities.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.HashMap;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

@Controller
public class LocationsController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations/search")
    public String getLocationSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {
        return "locations/search";
    }

    @GetMapping("/locations/results")
    public String getLocationResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {

        LocationQueryService LQS = new LocationQueryService();

        model.addAttribute("locSearch", locSearch);
        String json = LQS.getJSON(locSearch.getLocation());
        model.addAttribute("json",json);
        List<Place> Places = Place.listFromJson(json);
        model.addAttribute("Places",Places);
        return "locations/results";
    }

    @GetMapping("/locations")
    public String index(Model model, OAuth2AuthenticationToken token) {
        String uid = token.getPrincipal().getAttributes().get("id").toString();
        Iterable<Location> locations= locationRepository.findByUid(uid);
        model.addAttribute("locations", locations);
        return "locations/index";
    }

    @PostMapping("/locations/add")
    public String add(Location location, Model model, OAuth2AuthenticationToken token) {
      String uid = token.getPrincipal().getAttributes().get("id").toString();
      location.setUid(uid);
      locationRepository.save(location);
      model.addAttribute("locations", locationRepository.findByUid(uid));
      return "locations/index";
    }

    @DeleteMapping("/locations/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
    Location location = locationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid courseoffering Id:" + id));
    locationRepository.delete(location);
    model.addAttribute("locations", locationRepository.findByUid(location.getUid()));
    return "locations/index";
    }

    @GetMapping("/locations/admin")
    public String admin(Model model) {
        Iterable<Location> locations= locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "locations/admin";
    }

}
