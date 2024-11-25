package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class AdminController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/admin")
    public String showForm() {
        return "admin";
    }

    @PostMapping("/admin")
    public String addSong(@RequestParam("name") String name,
                          @RequestParam("author") String author,
                          @RequestParam("link") String link,
                          @RequestParam("cover") String cover,
                          @RequestParam(value = "favorite", required = false, defaultValue = "false") boolean favorite,
                          Model model) {
        Song newSong = new Song();
        newSong.setName(name);
        newSong.setAuthor(author);
        newSong.setLink(link);
        newSong.setCover(cover);
        newSong.setFavorite(favorite);

        songRepository.save(newSong);

        model.addAttribute("name", name);
        model.addAttribute("author", author);
        model.addAttribute("link", link);
        model.addAttribute("cover", cover);

        return "success";
    }
}