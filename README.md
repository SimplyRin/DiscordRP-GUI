# DiscordRP-GUI
GUI で Discord Rich Presence を設定できる物

アプリケーションを閉じる時、設定したテキストを自動的に config.yml に保存し起動時、自動的にテキストフィールドにデータをテキストフィールドにセットしてくれます。

Application ID (Client ID) の取得方法:
- 1: [Discord Developer Portal](https://discordapp.com/developers/applications/) に移動します。

- 2: `Create an application` をクリック

- 3: `Name` にゲーム名に設定したい名前を入力します

- 4: 変更が終わったら `CLIENT ID` をコピー

- 5: `DiscordRP GUI` の `Application ID` フィールドに貼り付けてお好みに設定したあと `Connect` をクリックしたら完了です。

# Download
v1.0: [DiscordRP-GUI-1.0.jar](https://github.com/SimplyRin/DiscordRP-GUI/releases/download/1.0/DiscordRP-GUI-1.0.jar)

**※: ダウンロード、使用は自己責任です。作者は問題が発生した場合でも責任を負いません。**

# Config
```Yaml
Application-ID: null
Details: null
State: null
Small-Image-Key: null
Small-Image-Text: null
Large-Image-Key: null
Large-Image-Text: null
```

※ なにもセットしていない場合は `{}` だけになります。

# Requiresments
- Discord Client
- Discord Application ID ([Developer Portal](https://discordapp.com/developers/applications/))

# Screenshot
![a16199f3fbb877174f2d68000679ccfe](https://i.gyazo.com/a16199f3fbb877174f2d68000679ccfe.png "a16199f3fbb877174f2d68000679ccfe")

# Open Source License
**・[BungeeCord (Config API) | BSD 3-Clause "New" or "Revised" License](https://github.com/SpigotMC/BungeeCord/blob/master/LICENSE)**
```
Copyright (c) 2012, md_5. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

The name of the author may not be used to endorse or promote products derived
from this software without specific prior written permission.

You may not use the software for commercial software hosting services without
written permission from the author.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
```

**・[DiscordIPC | Apache License 2.0](https://github.com/jagrosh/DiscordIPC/blob/master/LICENSE)**
```
Copyright 2017 John Grosh (john.a.grosh@gmail.com).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
