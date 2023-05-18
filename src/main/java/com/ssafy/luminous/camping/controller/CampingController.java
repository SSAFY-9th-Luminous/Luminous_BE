package com.ssafy.luminous.camping.controller;

import com.ssafy.luminous.camping.util.api.CampingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CampingController {

    private final CampingUtil campingUtil;

    @GetMapping("/camping")
    public void callStoreCamping() {
        try {
            campingUtil.storeCamping();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }



}
