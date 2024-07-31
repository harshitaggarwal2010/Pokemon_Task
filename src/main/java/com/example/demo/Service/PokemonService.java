package com.example.demo.Service;


import com.example.demo.DTO.RequestDTO.Pokemon;
import com.example.demo.DTO.RequestDTO.PokemonInfo;
import com.example.demo.DTO.ResponseDTO.PokemonListDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@Service
public class PokemonService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${external.pokemon.api.url}")
    private String POKEMON_URL;


    public PokemonListDTO listAllPokemon()  {

        String externalApiResponse = restTemplate.getForObject(POKEMON_URL, String.class);
        Pokemon pokemon = new Pokemon();

        try {
            pokemon = objectMapper.readValue(externalApiResponse, Pokemon.class);
        } catch (Exception e) {
            System.out.println("problem with api response");
        }

        Map<String, String> response = new HashMap<>();
        for(PokemonInfo pInfo : pokemon.getPokemons()) {
            response.put(pInfo.getPokeInfo().getName(), pInfo.getPokeInfo().getUrl());
        }

        PokemonListDTO pokemonListDTO = new PokemonListDTO();
        pokemonListDTO.setPokemonList(response);

        return pokemonListDTO;
    }
}
