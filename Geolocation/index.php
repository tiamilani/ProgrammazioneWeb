<!DOCTYPE html>
<html>
    <head>
        <title>Raspberry Pi - Weather Station</title>
        <meta http-equiv="refresh" content="21600"/>
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
        <link href="build/css/metro.css" rel="stylesheet">
        <link href="build/css/metro-responsive.css" rel="stylesheet">
        <link href="build/css/metro-icons.css" rel="stylesheet">
        <link href="build/css/metro-schemes.css" rel="stylesheet">
        <script src="build/js/jquery.js"></script>
        <script src="build/js/metro.js"></script>
        <style>
            .posTop {
                float: left;
                width: 100%;
            }

            .posWelcome {
                float: left;
                width: 100%;
                height: 20%;
                text-align: center;
            }

            .posLeftPc {
                float: left;
                text-align: center;
                margin-top: 10%;
                margin-left: auto;
                margin-right: auto;
                background-color: #3c3f41;
                width: 45%;
                height: 210px;
            }

            .posRightPc {
                float: right;
                text-align: center;
                margin-top: 10%;
                margin-left: auto;
                margin-right: auto;
                background-color: #3c3f41;
                width: 45%;
                height: 210px;
            }

            .posLeftPhone {
                width: 100%;
                text-align: center;
                margin-top: 10%;
                margin-left: auto;
                margin-right: auto;
                display:block;
                background-color: #3c3f41;
                height: 210px;
            }

            .posRightPhone {
                width: 100%;
                text-align: center;
                margin-top: 10%;
                margin-left: auto;
                margin-right: auto;
                display:block;
                background-color: #3c3f41;
                height: 210px;
            }
        </style>
    </head>

    <body class="metro">
        <div class="container">
            <div class="posTop">
                <div class="app-bar darcula" data-role="appbar">
                    <ul class="app-bar-menu">
                        <li data-flexorderorigin="0" data-flexorder="1"><a href="index.php">Home</a></li>
                        <span class="app-bar-divider"></span>
                        <li data-flexorderorigin="1" data-flexorder="2"><a href="live.php">Live</a></li>
                        <li data-flexorderorigin="2" data-flexorder="3"><a href="archive.php">Archivio</a></li>
			<li data-flexorderorigin="3" data-flexorder="4"><a href="altroMeteo.html">Altro Meteo</a></li>
                    </ul>
                    <div class="app-bar-pullbutton automatic" style="display: none;"></div>
                    <div class="clearfix" style="width: 0;"></div>
                    <nav class="app-bar-pullmenu hidden flexstyle-app-bar-menu" style="display: none;">
                        <ul class="app-bar-pullmenubar app-bar-menu hidden" style="display: none;"></ul>
                    </nav>
                </div>
            </div>
            <div class="posWelcome">
                <h1>Raspberry Pi Weather Station</h1>
            </div>
            <div class="no-phone">
                <a href="live.php">
                    <div class="tile-wide fg-white posLeftPc" data-role="tile">
                        <div class="tile-content iconic">
                            <h1>Live</h1>
                            <?php
                                if(date("G") > 19 || date("G") < 7)
                                    echo '<span class="icon mif-weather2"></span>';
                                else
                                    echo '<span class="icon mif-weather"></span>';
                            ?>
                        </div>
                    </div>
                </a>
                <a href="archive.php">
                    <div class="tile-wide fg-white posRightPc" data-role="tile">
                        <div class="tile-content iconic">
                            <h1>Archivio</h1>
                            <span class="icon mif-database"></span>
                        </div>
                    </div>
                </a>
            </div>

            <div class="no-pc">
                <a href="live.php">
                    <div class="tile-wide fg-white posLeftPhone" data-role="tile">
                        <div class="tile-content iconic">
                            <h1>Live</h1>
                            <?php
                                if(date("G") > 19 || date("G") < 7)
                                    echo '<span class="icon mif-weather2"></span>';
                                else
                                    echo '<span class="icon mif-weather"></span>';
                            ?>
                        </div>
                    </div>
                </a>
                <a href="archive.php">
                    <div class="tile-wide fg-white posRightPhone" data-role="tile">
                        <div class="tile-content iconic">
                            <h1>Archivio</h1>
                            <span class="icon mif-database"></span>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </body>
</html>
