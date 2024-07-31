package com.example.demo.Controller;

import com.example.demo.DTO.ResponseDTO.PokemonListDTO;
import com.example.demo.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pokemon/")
public class PokemonController {

    PokemonService pokemonService;

    @Autowired
    PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping("listAll")
    public Map<String, String> getALlPokemon() {
        return pokemonService.listAllPokemon().getPokemonList();
    }

}
