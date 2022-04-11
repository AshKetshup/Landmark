<div id="top"></div>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/AshKetshup/Landmark">
    <img src="img/Landmark-Logotype.png" alt="Logotype">
  </a>

<h3 align="center">Landmark</h3>

  <p align="center">
    A Java TUI Navigation Library
    <br />
    <br />
    <a href="https://github.com/AshKetshup/Landmark">View Demo</a>
    ·
    <a href="https://github.com/AshKetshup/Landmark/issues">Report Bug</a>
    ·
    <a href="https://github.com/AshKetshup/Landmark/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <!-- <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li> -->
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Landmark Screen Shot][product-screenshot]](https://example.com)


<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [Java](https://java.com/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

How to initialize your program
```Java
public static void main(String[] args) {
    ScreenManager sM = new ScreenManager();
    Navigation nav = new Navigation(
        sM,
        3       // amount of items shown for each page.
    );

    declareScreens(sM);     // Method used to declare all Screens
    
    // Binds a given Screen. You can get a Screen by using a getMenu or getArticle method given by the ScreenManager.
    sM.bindScreen(sM.getMenu("welcome_menu"));  

    // Starts the Loop with the given Articles and Menus
    nav.loop();
}
```

Example on how to declare menus and articles
```Java
public static void declareScreens(ScreenManager sM) {
    sM.addMenu(
        "welcome_menu",
        new Menu(
            "Hello World this is the title of the Welcome Menu",
            Arrays.asList(
                new Option(
                    "Article #1",       // Description 
                    () -> { sM.bindScreen(sM.getArticle("article_1")); }    // What is executed.
                ),
                new Option(
                    "Article #2",       // Description
                    () -> { sM.bindScreen(sM.getArticle("article_2")); }    // What is executed.
                ),
                new Option(
                    "Next Menu",       // Description 
                    () -> { sM.bindScreen(sM.getMenu("main_menu")); }       // What is executed.
                )
            ),
            sM
        )
    );

    sM.addArticle(
        "article_1",
        new Article(
            "This is the title of the Article #1",
            Arrays.asList(
                new StringStyler(
                    "This is the 1st line of the Article of color a bright blinking white\n", // Text
                    StringStyler.WHITE, // Color
                    StringStyler.BLINK, // Mode
                    true                // Bright?
                ),
                new StringStyler(
                    "This is the 2nd line of the Article of color green with a normal style and not bright",
                    StringStyler.WHITE,
                    StringStyler.NORMAL,
                    false
                )
            ),
            sM
        )
    );

    (...)
}
```

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [X] StringStyler Class
- [X] Notification System
    - [X] Critical Notification
    - [X] Valid Notification
    - [X] Warning Notification
    - [X] Tip Notification
- [X] Screens
    - [X] Menus
    - [X] Articles
- [X] CallStack
- [X] Commands
    - [X] Default Commands
    - [X] Screen Commands
- [ ] Forms
    - [ ] Login 
    - [ ] Register 
    - [ ] Custom 
- [ ] File Reader
    - [ ] Markdown Parser
    - [ ] Markdown Highlighter

See the [open issues](https://github.com/AshKetshup/Landmark/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the GNU General Public License v3.0. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Diogo Simões - dsimoes2000@gmail.com

Project Link: [https://github.com/AshKetshup/Landmark](https://github.com/AshKetshup/Landmark)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS 
## Acknowledgments

* []()
* []()
* []()
-->

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/AshKetshup/Landmark.svg?style=for-the-badge
[contributors-url]: https://github.com/AshKetshup/Landmark/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/AshKetshup/Landmark.svg?style=for-the-badge
[forks-url]: https://github.com/AshKetshup/Landmark/network/members
[stars-shield]: https://img.shields.io/github/stars/AshKetshup/Landmark.svg?style=for-the-badge
[stars-url]: https://github.com/AshKetshup/Landmark/stargazers
[issues-shield]: https://img.shields.io/github/issues/AshKetshup/Landmark.svg?style=for-the-badge
[issues-url]: https://github.com/AshKetshup/Landmark/issues
[license-shield]: https://img.shields.io/github/license/AshKetshup/Landmark.svg?style=for-the-badge
[license-url]: https://github.com/AshKetshup/Landmark/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/diogo-simoes-8b5a0618a
[product-screenshot]: images/screenshot.png