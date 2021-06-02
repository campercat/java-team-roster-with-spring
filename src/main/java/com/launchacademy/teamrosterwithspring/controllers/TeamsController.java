package com.launchacademy.teamrosterwithspring.controllers;

import com.launchacademy.teamrosterwithspring.models.League;
import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.List;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/")
public class TeamsController {
  @GetMapping
  public String getTeams(Model model) {
    League league = League.getLeague();
    List<Team> teams = league.getTeams();
    model.addAttribute("teams", teams);
    return "teams/index";
  }

  @GetMapping("/teams/{teamIndex}")
  public String getTeamDetails(@PathVariable int teamIndex, Model model) {
    League league = League.getLeague();
    List<Team> teams = league.getTeams();
    if(teamIndex < teams.size()) {
      Team selectedTeam = teams.get(teamIndex);
      model.addAttribute("selectedTeam", selectedTeam);
      return "teams/show";
    } else {
      System.out.println("out of bound");
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}
