package com.poc.portfolio.controller;

import static com.poc.portfolio.enums.BackendTech.JAVA;
import static com.poc.portfolio.enums.BackendTech.NODE;
import static com.poc.portfolio.enums.BackendTech.SPRING;
import static com.poc.portfolio.enums.DatabaseTech.MYSQL;
import static com.poc.portfolio.enums.DatabaseTech.POSTGRESQL;
import static com.poc.portfolio.enums.ExperienceLevel.EXPERIENCED;
import static com.poc.portfolio.enums.ExperienceLevel.INTERMEDIATE;
import static com.poc.portfolio.enums.FrontendTech.CSS;
import static com.poc.portfolio.enums.FrontendTech.HTML;
import static com.poc.portfolio.enums.FrontendTech.JS;
import static com.poc.portfolio.enums.FrontendTech.REACT;
import static com.poc.portfolio.enums.Titles.DANCER;
import static com.poc.portfolio.enums.Titles.PUBLIC_SPEAKER;
import static com.poc.portfolio.enums.Titles.SOFTWARE_DEVELOPMENT_ENGINEER;
import static com.poc.portfolio.utils.Constants.ABOUT_PIC_ASSET_URL;
import static com.poc.portfolio.utils.Constants.ARROW_ASSET_URL;
import static com.poc.portfolio.utils.Constants.CHECKMARK_ASSET_URL;
import static com.poc.portfolio.utils.Constants.EDUCATION_ASSET_URL;
import static com.poc.portfolio.utils.Constants.EMAIL_ASSET_URL;
import static com.poc.portfolio.utils.Constants.EXPERIENCE_ASSET_URL;
import static com.poc.portfolio.utils.Constants.GEHU_ONLINE;
import static com.poc.portfolio.utils.Constants.GEHU_ONLINE_ASSET_URL;
import static com.poc.portfolio.utils.Constants.GEHU_ONLINE_GITHUB_URL;
import static com.poc.portfolio.utils.Constants.GEHU_ONLINE_LIVE_DEMO_URL;
import static com.poc.portfolio.utils.Constants.GITHUB_ASSET_URL;
import static com.poc.portfolio.utils.Constants.GITHUB_URL;
import static com.poc.portfolio.utils.Constants.LEETCODE_ASSET_URL;
import static com.poc.portfolio.utils.Constants.LEETCODE_URL;
import static com.poc.portfolio.utils.Constants.LIBRBARY;
import static com.poc.portfolio.utils.Constants.LIBRBARY_ASSET_URL;
import static com.poc.portfolio.utils.Constants.LIBRBARY_GITHUB_URL;
import static com.poc.portfolio.utils.Constants.LIBRBARY_LIVE_DEMO_URL;
import static com.poc.portfolio.utils.Constants.LINKEDIN_ASSET_URL;
import static com.poc.portfolio.utils.Constants.LINKEDIN_URL;
import static com.poc.portfolio.utils.Constants.MAIL_TO_EMAIL;
import static com.poc.portfolio.utils.Constants.MY_EMAIL_ID;
import static com.poc.portfolio.utils.Constants.MY_EXPERIENCE_IN_YEARS;
import static com.poc.portfolio.utils.Constants.MY_NAME;
import static com.poc.portfolio.utils.Constants.POC_BANNER_URL;
import static com.poc.portfolio.utils.Constants.PROFILE_PIC_ASSET_URL;
import static com.poc.portfolio.utils.Constants.RESUME_FILENAME;
import static com.poc.portfolio.utils.Constants.RESUME_FILE_PATH;
import static com.poc.portfolio.utils.Constants.RESUME_MEDIA_TYPE;
import static com.poc.portfolio.utils.Constants.SECURE_FILE_UPLOAD;
import static com.poc.portfolio.utils.Constants.SECURE_FILE_UPLOAD_ASSET_URL;
import static com.poc.portfolio.utils.Constants.SECURE_FILE_UPLOAD_GITHUB_URL;
import static com.poc.portfolio.utils.Constants.SECURE_FILE_UPLOAD_LIVE_DEMO_URL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.poc.portfolio.experience.TechExperience;
import com.poc.portfolio.img.arrow.ArrowImgTag;
import com.poc.portfolio.img.project.ProjectImgTag;
import com.poc.portfolio.img.social.SocialMediaImgTag;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {

  @GetMapping("/")
  public String portfolio(Model model) {
    model.addAttribute("myName", MY_NAME);
    model.addAttribute("myExpInYear", MY_EXPERIENCE_IN_YEARS);
    model.addAttribute("myEmail", MY_EMAIL_ID);
    model.addAttribute("titles", getTitles());
    model.addAttribute("social", getSocialMediaImgTagAttributes());
    model.addAttribute("projects", getProjectImgTagAttributes());
    model.addAttribute("profilepic", PROFILE_PIC_ASSET_URL);
    model.addAttribute("aboutpic", ABOUT_PIC_ASSET_URL);
    model.addAttribute("experience", EXPERIENCE_ASSET_URL);
    model.addAttribute("education", EDUCATION_ASSET_URL);
    model.addAttribute("arrow", getArrowImgTagAttributes());
    model.addAttribute("checkmark", CHECKMARK_ASSET_URL);
    model.addAttribute("frontend", getFrontendDevelopmentExperience());
    model.addAttribute("backend", getBackendDevelopmentExperience());
    model.addAttribute("database", getDatabaseExperience());
    model.addAttribute("banner", POC_BANNER_URL);
    model.addAttribute("linkedin", LINKEDIN_URL);
    model.addAttribute("mailtoemail", MAIL_TO_EMAIL);
    model.addAttribute("email", EMAIL_ASSET_URL);
    model.addAttribute("linkedinpic", LINKEDIN_ASSET_URL);

    return "index";
  }

  @GetMapping(path = "/download")
  public ResponseEntity<Resource> downloadResume() throws IOException {
    File file = new File(RESUME_FILE_PATH);
    Path path = Paths.get(file.getAbsolutePath());
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + RESUME_FILENAME + "\"")
        .contentLength(file.length()).contentType(MediaType.parseMediaType(RESUME_MEDIA_TYPE)).body(resource);
  }

  private static List<String> getTitles() {
    List<String> listOfTitles = new ArrayList<>();
    listOfTitles.add(SOFTWARE_DEVELOPMENT_ENGINEER.getValue());
    listOfTitles.add(PUBLIC_SPEAKER.getValue());
    listOfTitles.add(DANCER.getValue());

    return listOfTitles;
  }

  private static List<SocialMediaImgTag> getSocialMediaImgTagAttributes() {
    List<SocialMediaImgTag> imgTagList = new ArrayList<>();
    imgTagList.add(SocialMediaImgTag.builder().assetUrl(LINKEDIN_ASSET_URL).altName("My LinkedIn profile")
        .onClickAction(LINKEDIN_URL).build());
    imgTagList.add(SocialMediaImgTag.builder().assetUrl(GITHUB_ASSET_URL).altName("My Github profile")
        .onClickAction(GITHUB_URL).build());
    imgTagList.add(SocialMediaImgTag.builder().assetUrl(LEETCODE_ASSET_URL).altName("My Leetcode profile")
        .onClickAction(LEETCODE_URL).build());

    return imgTagList;
  }

  private static List<ProjectImgTag> getProjectImgTagAttributes() {
    List<ProjectImgTag> imgTagList = new ArrayList<>();

    imgTagList.add(ProjectImgTag.builder().assetUrl(LIBRBARY_ASSET_URL).altName(LIBRBARY).projectName(LIBRBARY)
        .onGithubClickAction(LIBRBARY_GITHUB_URL).onLiveDemoClickAction(LIBRBARY_LIVE_DEMO_URL).build());

    imgTagList.add(ProjectImgTag.builder().assetUrl(SECURE_FILE_UPLOAD_ASSET_URL).altName(SECURE_FILE_UPLOAD)
        .projectName(SECURE_FILE_UPLOAD).onGithubClickAction(SECURE_FILE_UPLOAD_GITHUB_URL)
        .onLiveDemoClickAction(SECURE_FILE_UPLOAD_LIVE_DEMO_URL).build());

    imgTagList.add(ProjectImgTag.builder().assetUrl(GEHU_ONLINE_ASSET_URL).altName(GEHU_ONLINE)
        .projectName(GEHU_ONLINE).onGithubClickAction(GEHU_ONLINE_GITHUB_URL)
        .onLiveDemoClickAction(GEHU_ONLINE_LIVE_DEMO_URL).build());

    return imgTagList;
  }

  private static ArrowImgTag getArrowImgTagAttributes() {
    return ArrowImgTag.builder().assetUrl(ARROW_ASSET_URL).altName("Arrow icon").build();
  }

  private static List<TechExperience> getFrontendDevelopmentExperience() {
    List<TechExperience> imgTagList = new ArrayList<>();

    imgTagList.add(TechExperience.builder().technology(HTML.getValue()).expLevel(EXPERIENCED.getValue()).build());
    imgTagList.add(TechExperience.builder().technology(CSS.getValue()).expLevel(EXPERIENCED.getValue()).build());
    imgTagList.add(TechExperience.builder().technology(REACT.getValue()).expLevel(INTERMEDIATE.getValue()).build());
    imgTagList.add(TechExperience.builder().technology(JS.getValue()).expLevel(EXPERIENCED.getValue()).build());
    return imgTagList;
  }

  private static List<TechExperience> getBackendDevelopmentExperience() {
    List<TechExperience> imgTagList = new ArrayList<>();

    imgTagList.add(TechExperience.builder().technology(JAVA.getValue()).expLevel(EXPERIENCED.getValue()).build());
    imgTagList.add(TechExperience.builder().technology(NODE.getValue()).expLevel(INTERMEDIATE.getValue()).build());
    imgTagList.add(TechExperience.builder().technology(SPRING.getValue()).expLevel(EXPERIENCED.getValue()).build());

    return imgTagList;
  }

  private static List<TechExperience> getDatabaseExperience() {
    List<TechExperience> imgTagList = new ArrayList<>();

    imgTagList.add(
        TechExperience.builder().technology(POSTGRESQL.getValue()).expLevel(EXPERIENCED.getValue()).build());
    imgTagList.add(TechExperience.builder().technology(MYSQL.getValue()).expLevel(EXPERIENCED.getValue()).build());

    return imgTagList;
  }

}
