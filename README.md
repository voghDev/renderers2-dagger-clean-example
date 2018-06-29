# Deprecation alert

This repository is no longer maintained. Use it for read and research purposes only.
If you want to see Android architectural approaches, have a look at these repos:

[dagger2-clean-mvp-example - Using newer versions of the tools (Java)](https://github.com/voghDev/dagger2-clean-mvp-example)
[ChuckNorrisJokes - Querying an API using a TDD-based approach (Kotlin)](https://github.com/voghDev/ChuckNorrisJokes)
[PlayBattlegrounds - Real case study of consuming PUBG Open API (Kotlin)](https://github.com/voghDev/PlayBattlegrounds)

# Renderers2 Example - Another approach to CLEAN

Alternative to my [first approach to CLEAN+MVP+DI][1], again based on awesome work from respected developers.
Uses a newer version of [Renderers][2] to support RecyclerView instead of ListView.
Features a dummy list of videos, and they can be "highlighted" clicking on the dummy thumbnail. The customers who highlight the videos are also dummies, taken from a dummy API. As you can see, this is a very serious project, no jokes on it.
Some of the aspects covered on this project are:

* Dependency Injection using [Dagger][3].
* View injection with [ButterKnife][4].
* Improved Threading management, using fat Interactors + fat DataSources. Based on [EffectiveAndroidUI][5],
* Rendering multiple entities in the same adapter.
* Parsing XML entities using [retrofit][6] + [SimpleXml][7].

Once more, this project wouldn't have been possible without previous sample projects from various developers. See the [attributions][1] section.

Future TODOs

* Integrating [retrofit-converters][8] to parse XML in an even-simpler way
* Database storage
* TDD / A decent test coverage of the code

Developed By
------------

* Olmo Gallegos Hernández - [@voghDev][9] - [mobiledevstories.com][10]

<a href="http://twitter.com/voghDev">
  <img alt="Follow me on Twitter" src="https://image.freepik.com/iconos-gratis/twitter-logo_318-40209.jpg" height="60" width="60" />
</a>
<a href="https://www.linkedin.com/profile/view?id=91543271">
  <img alt="Find me on Linkedin" src="https://image.freepik.com/iconos-gratis/boton-del-logotipo-linkedin_318-84979.png" height="60" width="60" />
</a>

# License

    Copyright 2015 Olmo Gallegos Hernández

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: https://github.com/voghDev/dagger2-clean-mvp-example
[2]: https://github.com/pedrovgs/Renderers
[3]: https://github.com/google/dagger
[4]: https://github.com/JakeWharton/butterknife
[5]: https://github.com/pedrovgs/EffectiveAndroidUI
[6]: https://github.com/square/retrofit
[7]: http://simple.sourceforge.net/
[8]: https://github.com/square/retrofit/tree/master/retrofit-converters
[9]: http://twitter.com/voghDev
[10]: http://www.mobiledevstories.com
