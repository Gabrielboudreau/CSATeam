package com.nighthawk.spring_portfolio.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageConverter {

    @GetMapping("/imageconvert")
    public String convertGet(Model model) {
        return "imageconvert"; 
    }

    @PostMapping("/imageconvert")
    public String convertPost(@RequestParam(name="type", required=true) String type, @RequestParam("image") MultipartFile multipartFile, Model model) {
      BufferedImage imBuff;
      try {
        imBuff = ImageIO.read(multipartFile.getInputStream());
      } catch (IOException e) {
        System.err.println("error occured wow sad cry about it");
        return "imageconvert";
      }
      
      BufferedImage output = imBuff;
      ImageConversion converter = new ImageConversion();
      if (type.equals("ascii")) {
        model.addAttribute("ascii", converter.ascii(imBuff));
        return "imageconvert";
      } else if (type.equals("gray")) {
        output = converter.grayscale(imBuff);
      } else if (type.equals("red")) {
        output = converter.redscale(imBuff);
      } else if (type.equals("green")) {
        output = converter.greenscale(imBuff);
      } else if (type.equals("blue")) {
        output = converter.bluescale(imBuff);
      }

      String uniqueID = UUID.randomUUID().toString();
      File outputfile = new File("target/classes/static/images/" + uniqueID + ".png");
      try {
        outputfile.createNewFile();
        if (output != null) ImageIO.write(output, "png", outputfile);
      } catch (IOException e) {
        return "imageconvert";
      }
      
      model.addAttribute("image", "images/"+uniqueID+".png");

      return "imageconvert"; 
    }
}