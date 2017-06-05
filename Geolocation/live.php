<!DOCTYPE html>
<html>
    <head>
        <title>Meteo Live</title>
        <meta http-equiv="refresh" content="3600">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="build/css/metro.css" rel="stylesheet">
        <link href="build/css/metro-icons.css" rel="stylesheet">
        <link href="build/css/metro-responsive.css" rel="stylesheet">
        <link href="build/css/metro-schemes.css" rel="stylesheet">
        <link href="css/weather-icons.css" rel="stylesheet">
        <link href="css/weather-icons-wind.css" rel="stylesheet">
        <script src="build/js/jquery.js"></script>
        <script src="build/js/metro.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script>
        $(document).ready(function(){
            if (navigator.geolocation){
                navigator.geolocation.getCurrentPosition(showLocation);
            } else {
                $('#location').html('Via Stazione, 24, 36028 Rossano Veneto VI, Italy');
            }
        });

        function showLocation(position) {
        	var latitude = position.coords.latitude;
        	var longitude = position.coords.longitude;
        	$.ajax({
        		type:'POST',
        		url:'getLocation.php',
        		data:'latitude='+latitude+'&longitude='+longitude,
        		success:function(msg){
                    if(msg){
                       $("#location").html(msg);
                    }else{
                        $("#location").html('Via Stazione, 24, 36028 Rossano Veneto VI, Italy');
                    }
        		}
        	});
        }
        </script>
        <script>
            $(document).ready(function(){
                $("#letturaDiv").click(function(){
                    $("#letturaDiv").slideUp();
                });
            });
        </script>
        <style>
        .container {
            height: auto;
        }

        .posTop {
            float: left;
            width: 100%;
        }

        .posOn {
            float: left;
            width: 100%;
            height: auto;
            text-align: center;
        }

        .posLeft {
            float: left;
            width: 50%;
            height: 50%;
            text-align: center;
            margin-left: 0px;
            margin-right: auto;
        }
        .posRight {
            float: right;
            width: 50%;
            height: 50%;
            text-align: center;
            margin-right: 0px;
            margin-left: auto;
        }
        </style>
    </head>
    <body class="metro">
        <?php
            $idWeatherDay = array("wi-day-sunny", "wi-day-cloudy", "wi-cloud", "wi-cloudy", "wi-day-sprinkle", "wi-day-showers", "wi-rain", "wi-day-rain", "wi-day-rain", "wi-rain", "wi-day-storm-showers", "wi-day-thunderstorm", "wi-thunderstorm", "wi-day-sleet-storm", "wi-day-sleet-storm", "wi-hail", "wi-day-snow", "wi-day-snow", "wi-snow");
            $idWeatherNight = array("wi-night-clear", "wi-night-alt-cloudy", "wi-cloud", "wi-cloudy", "wi-night-alt-sprinkle", "wi-night-alt-showers", "wi-rain", "wi-night-rain", "wi-night-rain", "wi-rain", "wi-night-storm-showers", "wi-night-thunderstorm", "wi-thunderstorm", "wi-night-sleet-storm", "wi-night-sleet-storm", "wi-hail", "wi-night-snow", "wi-night-snow", "wi-snow");
            $idWind = array("wi-wind towards-180-deg", "wi-wind towards-225-deg", "wi-wind towards-270-deg", "wi-wind towards-313-deg", "wi-wind towards-0-deg", "wi-wind towards-45-deg", "wi-wind towards-90-deg", "wi-wind towards-135-deg");
        ?>
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

            <?php
                function mostraCompleanno($persona)
                {
                    echo '<div style="text-align: center; width: 100%; height: 200px;" class="fg-blue">';
                    echo '<h1 style="font-size: 500%;">Buon Compleanno<br/>' . $persona . '</h1>';
                    echo '</div>';
                }

                switch([Date("j"), Date("n")])
                {
                    case ['16', '2'] : mostraCompleanno("Nonno"); break;
                    case ['26', '2'] : mostraCompleanno("Nonna"); break;
                    case ['2', '3'] : mostraCompleanno("Zio"); break;
                    case ['20', '5'] : mostraCompleanno("Andrea"); break;
                    case ['26', '5'] : mostraCompleanno("Mamma"); break;
                    case ['12', '6'] : mostraCompleanno("Papa'"); break;
                    case ['29', '7'] : mostraCompleanno("Riccardo"); break;
                    //case ['25', '10'] : mostraCompleanno("Ilaria"); break;
                    default: break;
                }

                $lastday = Date("j", mktime(0, 0, 0, (Date("n")+1), 0, Date("Y")));
                if(Date("j") == $lastday-1 || Date("j") == $lastday)
                {
                    echo '<div id="letturaDiv" style="text-align: center; width: 100%; height: auto;">';
                    echo '<h1 style="font-size: 500%;" class="fg-blue">Autolettura Gas</h1></div>';
                }

                echo '<div class="posTop" style="text-align: center; width: 100%; height: auto;">';
                echo '<h1 style="font-size: 200%;" id="location" class="fg-blue"></h1></div>';
            ?>

            <?php
                if(isset($_COOKIE[cityId]))
                    $cod = $_COOKIE[cityId];
                else
                    $cod = "31035";

                if($xml = simplexml_load_file("http://api.ilmeteo.net/index.php?api_lang=it&localidad=" . $cod . "&affiliate_id=4z7c7es7kdqr"))
                {
                    $meteo = array(array(), array(), array(), array(), array(), array(), array());
                    $i = 0;

                    foreach ($xml->location->var as $var) {
                        switch ($i)
                        {
                            case 0:
                                $meteo[0]["tempMin"] = $xml->location->var[$i]->data->forecast[0]->attributes()->value;
                                $meteo[1]["tempMin"] = $xml->location->var[$i]->data->forecast[1]->attributes()->value;
                                $meteo[2]["tempMin"] = $xml->location->var[$i]->data->forecast[2]->attributes()->value;
                                $meteo[3]["tempMin"] = $xml->location->var[$i]->data->forecast[3]->attributes()->value;
                                $meteo[4]["tempMin"] = $xml->location->var[$i]->data->forecast[4]->attributes()->value;
                                $meteo[5]["tempMin"] = $xml->location->var[$i]->data->forecast[5]->attributes()->value;
                                $meteo[6]["tempMin"] = $xml->location->var[$i]->data->forecast[6]->attributes()->value;
                                break;
                            case 1:
                                $meteo[0]["tempMax"] = $xml->location->var[$i]->data->forecast[0]->attributes()->value;
                                $meteo[1]["tempMax"] = $xml->location->var[$i]->data->forecast[1]->attributes()->value;
                                $meteo[2]["tempMax"] = $xml->location->var[$i]->data->forecast[2]->attributes()->value;
                                $meteo[3]["tempMax"] = $xml->location->var[$i]->data->forecast[3]->attributes()->value;
                                $meteo[4]["tempMax"] = $xml->location->var[$i]->data->forecast[4]->attributes()->value;
                                $meteo[5]["tempMax"] = $xml->location->var[$i]->data->forecast[5]->attributes()->value;
                                $meteo[6]["tempMax"] = $xml->location->var[$i]->data->forecast[6]->attributes()->value;
                                break;
                            case 2:
                                $meteo[0]["windSymbolId"] = $xml->location->var[$i]->data->forecast[0]->attributes()->id;
                                $meteo[1]["windSymbolId"] = $xml->location->var[$i]->data->forecast[1]->attributes()->id;
                                $meteo[2]["windSymbolId"] = $xml->location->var[$i]->data->forecast[2]->attributes()->id;
                                $meteo[3]["windSymbolId"] = $xml->location->var[$i]->data->forecast[3]->attributes()->id;
                                $meteo[4]["windSymbolId"] = $xml->location->var[$i]->data->forecast[4]->attributes()->id;
                                $meteo[5]["windSymbolId"] = $xml->location->var[$i]->data->forecast[5]->attributes()->id;
                                $meteo[6]["windSymbolId"] = $xml->location->var[$i]->data->forecast[6]->attributes()->id;
                                $meteo[0]["windSymbolValue"] = $xml->location->var[$i]->data->forecast[0]->attributes()->value;
                                $meteo[1]["windSymbolValue"] = $xml->location->var[$i]->data->forecast[1]->attributes()->value;
                                $meteo[2]["windSymbolValue"] = $xml->location->var[$i]->data->forecast[2]->attributes()->value;
                                $meteo[3]["windSymbolValue"] = $xml->location->var[$i]->data->forecast[3]->attributes()->value;
                                $meteo[4]["windSymbolValue"] = $xml->location->var[$i]->data->forecast[4]->attributes()->value;
                                $meteo[5]["windSymbolValue"] = $xml->location->var[$i]->data->forecast[5]->attributes()->value;
                                $meteo[6]["windSymbolValue"] = $xml->location->var[$i]->data->forecast[6]->attributes()->value;
                                break;
                            case 3:
                                $meteo[0]["weatherSymbolId"] = $xml->location->var[$i]->data->forecast[0]->attributes()->id;
                                $meteo[1]["weatherSymbolId"] = $xml->location->var[$i]->data->forecast[1]->attributes()->id;
                                $meteo[2]["weatherSymbolId"] = $xml->location->var[$i]->data->forecast[2]->attributes()->id;
                                $meteo[3]["weatherSymbolId"] = $xml->location->var[$i]->data->forecast[3]->attributes()->id;
                                $meteo[4]["weatherSymbolId"] = $xml->location->var[$i]->data->forecast[4]->attributes()->id;
                                $meteo[5]["weatherSymbolId"] = $xml->location->var[$i]->data->forecast[5]->attributes()->id;
                                $meteo[6]["weatherSymbolId"] = $xml->location->var[$i]->data->forecast[6]->attributes()->id;
                                $meteo[0]["weatherSymbolValue"] = $xml->location->var[$i]->data->forecast[0]->attributes()->value;
                                $meteo[1]["weatherSymbolValue"] = $xml->location->var[$i]->data->forecast[1]->attributes()->value;
                                $meteo[2]["weatherSymbolValue"] = $xml->location->var[$i]->data->forecast[2]->attributes()->value;
                                $meteo[3]["weatherSymbolValue"] = $xml->location->var[$i]->data->forecast[3]->attributes()->value;
                                $meteo[4]["weatherSymbolValue"] = $xml->location->var[$i]->data->forecast[4]->attributes()->value;
                                $meteo[5]["weatherSymbolValue"] = $xml->location->var[$i]->data->forecast[5]->attributes()->value;
                                $meteo[6]["weatherSymbolValue"] = $xml->location->var[$i]->data->forecast[6]->attributes()->value;
                                break;
                            case 4:
                                $meteo[0]["dayName"] = $xml->location->var[$i]->data->forecast[0]->attributes()->value;
                                $meteo[1]["dayName"] = $xml->location->var[$i]->data->forecast[1]->attributes()->value;
                                $meteo[2]["dayName"] = $xml->location->var[$i]->data->forecast[2]->attributes()->value;
                                $meteo[3]["dayName"] = $xml->location->var[$i]->data->forecast[3]->attributes()->value;
                                $meteo[4]["dayName"] = $xml->location->var[$i]->data->forecast[4]->attributes()->value;
                                $meteo[5]["dayName"] = $xml->location->var[$i]->data->forecast[5]->attributes()->value;
                                $meteo[6]["dayName"] = $xml->location->var[$i]->data->forecast[6]->attributes()->value;
                                break;
                            case 5:
                                $meteo[0]["descriptionDay"] = $xml->location->var[$i]->data->forecast[0]->attributes()->value;
                                $meteo[1]["descriptionDay"] = $xml->location->var[$i]->data->forecast[1]->attributes()->value;
                                $meteo[2]["descriptionDay"] = $xml->location->var[$i]->data->forecast[2]->attributes()->value;
                                $meteo[3]["descriptionDay"] = $xml->location->var[$i]->data->forecast[3]->attributes()->value;
                                $meteo[4]["descriptionDay"] = $xml->location->var[$i]->data->forecast[4]->attributes()->value;
                                $meteo[5]["descriptionDay"] = $xml->location->var[$i]->data->forecast[5]->attributes()->value;
                                $meteo[6]["descriptionDay"] = $xml->location->var[$i]->data->forecast[6]->attributes()->value;
                                break;
                        }
                        $i++;
                    }
                }

                for($i = 0; $i < 7; $i++)
                {
                    $windTmp = $meteo[$i]["windSymbolId"]%8;
                    if($windTmp == 0)
                        $windTmp = 8;
                    if($windTmp == 33)
                        echo $meteo[$i]["windSymbolId"];
                    else
                        $meteo[$i]["windSymbolId"] = $windTmp;
                }
            ?>

        <?php
            if($xml)
            {
        ?>
            <div class="posOn no-phone">
                <div class="tile-wide fg-white" style="width: 100%; height: 150px; margin-right: auto; margin-left: auto; display: block;" data-role="tile" data-effect="slideDown" data-period="15000" data-duration="500">
                    <div class="tile-content">
                        <div class="live-slide" style="top: 0px; display: block;">
                            <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                    <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[0]["dayName"]); ?></h3>
                                </div>
                                <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                    <div class="tile-content iconic" style="float:left; top: 20%; height: 80%; width: 15%; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p>
                                        <?php
                                            echo '<i class="icon wi ';
                                            if(date("G") > 19 || date("G") < 7)
                                                $symbol = $idWeatherNight[$meteo[0]["weatherSymbolId"]-1];
                                            else
                                                $symbol = $idWeatherDay[$meteo[0]["weatherSymbolId"]-1];

                                            echo $symbol;
                                            if(strcmp($symbol, "wi-day-sunny") == 0 || strcmp($symbol, "wi-night-clear") == 0)
                                                echo ' fg-yellow';
                                            else if(strcmp($symbol, "wi-cloud") == 0)
                                                echo ' fg-lighterBlue';
                                            else if(strcmp($symbol, "wi-cloudy") == 0 || strcmp($symbol, "wi-rain") == 0)
                                                echo ' fg-lightGray';
                                            else if(strcmp($symbol, "wi-thunderstorm") == 0)
                                                echo ' fg-red';
                                            else if(strpos($symbol, "hail") || strpos($symbol, "snow"))
                                                echo ' fg-white';
                                            else
                                                echo ' fg-lighterGray';

                                            echo '"></i>';
                                        ?>
                                        </p>
                                    </div>
                                    <div style="float: left; height: 100%; width: 80%; text-align: center; margin: 0 0 0 20%;">
                                        <p style="position: relative; float: left; line-height: 120%; font-size: large; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">
                                            <?php echo $meteo[0]["descriptionDay"]; ?>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="live-slide" style="top: -150px; display: block;">
                            <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                    <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[0]["dayName"]); ?></h3>
                                </div>
                                <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                    <div style="float: left; height: 100%; width: 30%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Descrizione Meteo
                                        </p>
                                        <p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">
                                            <?php echo $meteo[0]["weatherSymbolValue"]; ?>
                                        </p>
                                    </div>
                                    <div style="float: left; height: 100%; width: 20%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Temp Max
                                        </p>
                                        <?php
                                            $temp = $meteo[0]["tempMax"];
                                            if($temp > 0 && $temp <= 15)
                                                echo '<p class="fg-lightBlue"';
                                            else if ($temp > 15 && $temp <= 25)
                                                echo '<p class="fg-yellow"';
                                            else if($temp > 25)
                                                echo '<p class="fg-amber"';
                                            else
                                                echo '<p class="fg-blue"';

                                            echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                            echo $temp . '°C';
                                        ?>
                                        </p>
                                    </div>
                                    <div style="float: left; height: 100%; width: 20%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Temp Min
                                        </p>
                                        <?php
                                            $temp = $meteo[0]["tempMin"];
                                            if($temp > 0 && $temp <= 15)
                                                echo '<p class="fg-lightBlue"';
                                            else if ($temp > 15 && $temp <= 25)
                                                echo '<p class="fg-yellow"';
                                            else if($temp > 25)
                                                echo '<p class="fg-amber"';
                                            else
                                                echo '<p class="fg-blue"';

                                            echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                            echo $temp . '°C';
                                        ?>
                                    </div>
                                    <div style="float: left; height: 100%; width: 30%; text-align: center;">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Descrizione Vento
                                        </p>
                                        <p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">
                                            <?php echo $meteo[0]["windSymbolValue"]; ?>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <?php
                $cont = 0;
                while($cont < 7)
                {
            ?>
            <div class="posOn no-pc">
                <div class="tile-wide fg-white" style="float: left; width: 100%; height: 150px; margin-right: auto; margin-left: auto; display: block;" data-role="tile" data-effect="slideDown" data-period="15000" data-duration="500">
                    <div class="tile-content">
                        <div class="live-slide" style="top: 0px; display: block;">
                            <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                    <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                </div>
                                <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                    <div class="tile-content iconic" style="float: left; top: 20%; height: 80%; width: 30%; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p>
                                        <?php
                                            echo '<i class="icon wi ';
                                            if(date("G") > 19 || date("G") < 7)
                                                $symbol = $idWeatherNight[$meteo[$cont]["weatherSymbolId"]-1];
                                            else
                                                $symbol = $idWeatherDay[$meteo[$cont]["weatherSymbolId"]-1];

                                            echo $symbol;
                                            if(strcmp($symbol, "wi-day-sunny") == 0 || strcmp($symbol, "wi-night-clear") == 0)
                                                echo ' fg-yellow';
                                            else if(strcmp($symbol, "wi-cloud") == 0)
                                                echo ' fg-lighterBlue';
                                            else if(strcmp($symbol, "wi-cloudy") == 0 || strcmp($symbol, "wi-rain") == 0)
                                                echo ' fg-lightGray';
                                            else if(strcmp($symbol, "wi-thunderstorm") == 0)
                                                echo ' fg-red';
                                            else if(strpos($symbol, "hail") || strpos($symbol, "snow"))
                                                echo ' fg-white';
                                            else
                                                echo ' fg-lighterGray';

                                            echo '"></i>';
                                        ?>
                                        </p>
                                    </div>
                                    <div style="float: left; height: 100%; width: 70%; text-align: center; margin: 0 0 0 30%;">
                                        <?php
                                            $length = strlen($meteo[$cont]["descriptionDay"]);
                                            if($length <= 100)
                                                echo '<p style="position: relative; line-height: 120%; font-size: large; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';
                                            else if($length > 100 && $length <= 125)
                                                echo '<p style="position: relative; line-height: 120%; font-size: medium; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';
                                            else if($length > 125 && $length <= 150)
                                                echo '<p style="position: relative; line-height: 120%; font-size: small; margin: 5% 0 0 0; height: auto;">';
                                            else
                                                echo '<p style="position: relative; line-height: 120%; font-size: smaller; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';

                                            echo $meteo[$cont]["descriptionDay"];
                                        ?>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="live-slide" style="top: -150px; display: block;">
                            <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                    <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                </div>
                                <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                    <div style="float: left; height: 100%; width: 70%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Descrizione Meteo
                                        </p>
                                        <?php
                                            $length = strlen($meteo[$cont]["weatherSymbolValue"]);
                                            if($length <= 100)
                                                echo '<p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">';
                                            else if($length > 100 && $length < 150)
                                                echo '<p style="position: relative; height: auto; line-height: 120%; font-size: medium; margin-left: 5%; margin-right: 5%;">';
                                            else
                                                echo '<p style="position: relative; height: auto; line-height: 120%; font-size: small; margin-left: 5%; margin-right: 5%;">';

                                            echo $meteo[$cont]["weatherSymbolValue"];
                                        ?>
                                        </p>
                                    </div>
                                    <div style="float: left; height: 100%; width: 30%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Temp Max
                                        </p>
                                        <?php
                                            $temp = $meteo[$cont]["tempMax"];
                                            if($temp > 0 && $temp <= 15)
                                                echo '<p class="fg-lightBlue"';
                                            else if ($temp > 15 && $temp <= 25)
                                                echo '<p class="fg-yellow"';
                                            else if($temp > 25)
                                                echo '<p class="fg-amber"';
                                            else
                                                echo '<p class="fg-blue"';

                                            echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                            echo $temp . '°C';
                                        ?>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="live-slide" style="top: 150px; display: block;">
                            <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                    <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                </div>
                                <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                    <div style="float: left; height: 100%; width: 30%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Temp Min
                                        </p>
                                        <?php
                                            $temp = $meteo[$cont]["tempMin"];
                                            if($temp > 0 && $temp <= 15)
                                                echo '<p class="fg-lightBlue"';
                                            else if ($temp > 15 && $temp <= 25)
                                                echo '<p class="fg-yellow"';
                                            else if($temp > 25)
                                                echo '<p class="fg-amber"';
                                            else
                                                echo '<p class="fg-blue"';

                                            echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                            echo $temp . '°C';
                                        ?>
                                    </div>
                                    <div style="float: left; height: 100%; width: 70%; text-align: center;">
                                        <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                            Descrizione Vento
                                        </p>
                                        <?php
                                            $length = strlen($meteo[$cont]["windSymbolValue"]);
                                            if($length <= 100)
                                                echo '<p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">';
                                            else if($length > 100 && $length < 150)
                                                echo '<p style="position: relative; height: auto; line-height: 120%; font-size: medium; margin-left: 5%; margin-right: 5%;">';
                                            else
                                                echo '<p style="position: relative; height: auto; line-height: 120%; font-size: small; margin-left: 5%; margin-right: 5%;">';

                                            echo $meteo[$cont]["windSymbolValue"];
                                        ?>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <?php
                    $cont++;
                }

                $num = 0;
                $cont = 1;

                while($num < 3)
                {
            ?>
                <div class="posOn no-phone">
                    <div class="tile-wide fg-white" style="float: left; width: 49%; height: 150px; margin-right: auto; margin-left: auto; display: block;" data-role="tile" data-effect="slideDown" data-period="15000" data-duration="500">
                        <div class="tile-content">
                            <div class="live-slide" style="top: 0px; display: block;">
                                <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                    <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                        <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                    </div>
                                    <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                        <div class="tile-content iconic" style="float: left; top: 20%; height: 80%; width: 30%; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p>
                                            <?php
                                                echo '<i class="icon wi ';
                                                if(date("G") > 19 || date("G") < 7)
                                                    $symbol = $idWeatherNight[$meteo[$cont]["weatherSymbolId"]-1];
                                                else
                                                    $symbol = $idWeatherDay[$meteo[$cont]["weatherSymbolId"]-1];

                                                echo $symbol;
                                                if(strcmp($symbol, "wi-day-sunny") == 0 || strcmp($symbol, "wi-night-clear") == 0)
                                                    echo ' fg-yellow';
                                                else if(strcmp($symbol, "wi-cloud") == 0)
                                                    echo ' fg-lighterBlue';
                                                else if(strcmp($symbol, "wi-cloudy") == 0 || strcmp($symbol, "wi-rain") == 0)
                                                    echo ' fg-lightGray';
                                                else if(strcmp($symbol, "wi-thunderstorm") == 0)
                                                    echo ' fg-red';
                                                else if(strpos($symbol, "hail") || strpos($symbol, "snow"))
                                                    echo ' fg-white';
                                                else
                                                    echo ' fg-lighterGray';

                                                echo '"></i>';
                                            ?>
                                            </p>
                                        </div>
                                        <div style="float: left; height: 100%; width: 70%; text-align: center; margin: 0 0 0 30%;">
                                            <?php
                                                $length = strlen($meteo[$cont]["descriptionDay"]);
                                                if($length <= 100)
                                                    echo '<p style="position: relative; line-height: 120%; font-size: large; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 100 && $length <= 130)
                                                    echo '<p style="position: relative; line-height: 120%; font-size: medium; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 130 && $length <= 160)
                                                    echo '<p style="position: relative; line-height: 120%; font-size: medium; margin: 5% 0 0 0; height: auto;">';
                                                else
                                                    echo '<p style="position: relative; line-height: 120%; font-size: small; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';

                                                echo $meteo[$cont]["descriptionDay"];
                                            ?>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="live-slide" style="top: -150px; display: block;">
                                <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                    <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                        <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                    </div>
                                    <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                        <div style="float: left; height: 100%; width: 70%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Descrizione Meteo
                                            </p>
                                            <?php
                                                $length = strlen($meteo[$cont]["weatherSymbolValue"]);
                                                if($length <= 100)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 100 && $length < 150)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: medium; margin-left: 5%; margin-right: 5%;">';
                                                else
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: small; margin-left: 5%; margin-right: 5%;">';

                                                echo $meteo[$cont]["weatherSymbolValue"];
                                            ?>
                                            </p>
                                        </div>
                                        <div style="float: left; height: 100%; width: 30%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Temp Max
                                            </p>
                                            <?php
                                                $temp = $meteo[$cont]["tempMax"];
                                                if($temp > 0 && $temp <= 15)
                                                    echo '<p class="fg-lightBlue"';
                                                else if ($temp > 15 && $temp <= 25)
                                                    echo '<p class="fg-yellow"';
                                                else if($temp > 25)
                                                    echo '<p class="fg-amber"';
                                                else
                                                    echo '<p class="fg-blue"';

                                                echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                                echo $temp . '°C';
                                            ?>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="live-slide" style="top: 150px; display: block;">
                                <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                    <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                        <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                    </div>
                                    <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                        <div style="float: left; height: 100%; width: 30%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Temp Min
                                            </p>
                                            <?php
                                                $temp = $meteo[$cont]["tempMin"];
                                                if($temp > 0 && $temp <= 15)
                                                    echo '<p class="fg-lightBlue"';
                                                else if ($temp > 15 && $temp <= 25)
                                                    echo '<p class="fg-yellow"';
                                                else if($temp > 25)
                                                    echo '<p class="fg-amber"';
                                                else
                                                    echo '<p class="fg-blue"';

                                                echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                                echo $temp . '°C';
                                            ?>
                                        </div>
                                        <div style="float: left; height: 100%; width: 70%; text-align: center;">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Descrizione Vento
                                            </p>
                                            <?php
                                                $length = strlen($meteo[$cont]["windSymbolValue"]);
                                                if($length <= 100)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 100 && $length < 150)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: medium; margin-left: 5%; margin-right: 5%;">';
                                                else
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: small; margin-left: 5%; margin-right: 5%;">';

                                                echo $meteo[$cont]["windSymbolValue"];
                                            ?>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <?php $cont++; ?>
                    <div class="tile-wide fg-white" style="float: right; width: 49%; height: 150px; margin-right: auto; margin-left: auto; display: block;" data-role="tile" data-effect="slideDown" data-period="15000" data-duration="500">
                        <div class="tile-content">
                            <div class="live-slide" style="top: 0px; display: block;">
                                <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                    <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                        <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                    </div>
                                    <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                        <div class="tile-content iconic" style="float: left; top: 20%; height: 80%; width: 30%; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p>
                                            <?php
                                                echo '<i class="icon wi ';
                                                if(date("G") > 19 || date("G") < 7)
                                                    $symbol = $idWeatherNight[$meteo[$cont]["weatherSymbolId"]-1];
                                                else
                                                    $symbol = $idWeatherDay[$meteo[$cont]["weatherSymbolId"]-1];

                                                echo $symbol;
                                                if(strcmp($symbol, "wi-day-sunny") == 0 || strcmp($symbol, "wi-night-clear") == 0)
                                                    echo ' fg-yellow';
                                                else if(strcmp($symbol, "wi-cloud") == 0)
                                                    echo ' fg-lighterBlue';
                                                else if(strcmp($symbol, "wi-cloudy") == 0 || strcmp($symbol, "wi-rain") == 0)
                                                    echo ' fg-lightGray';
                                                else if(strcmp($symbol, "wi-thunderstorm") == 0)
                                                    echo ' fg-red';
                                                else if(strpos($symbol, "hail") || strpos($symbol, "snow"))
                                                    echo ' fg-white';
                                                else
                                                    echo ' fg-lighterGray';

                                                echo '"></i>';
                                            ?>
                                            </p>
                                        </div>
                                        <div style="float: left; height: 100%; width: 70%; text-align: center; margin: 0 0 0 30%;">
                                            <?php
                                                $length = strlen($meteo[$cont]["descriptionDay"]);
                                                if($length <= 100)
                                                    echo '<p style="position: relative; line-height: 120%; font-size: large; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 100 && $length <= 130)
                                                    echo '<p style="position: relative; line-height: 120%; font-size: medium; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 130 && $length <= 160)
                                                    echo '<p style="position: relative; line-height: 120%; font-size: medium; margin: 5% 0 0 0; height: auto;">';
                                                else
                                                    echo '<p style="position: relative; line-height: 120%; font-size: small; margin: 5% 0 0 0; height: auto; margin-left: 5%; margin-right: 5%;">';

                                                echo $meteo[$cont]["descriptionDay"];
                                            ?>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="live-slide" style="top: -150px; display: block;">
                                <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                    <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                        <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                    </div>
                                    <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                        <div style="float: left; height: 100%; width: 70%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Descrizione Meteo
                                            </p>
                                            <?php
                                                $length = strlen($meteo[$cont]["weatherSymbolValue"]);
                                                if($length <= 105)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 100 && $length < 150)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: medium; margin-left: 5%; margin-right: 5%;">';
                                                else
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: small; margin-left: 5%; margin-right: 5%;">';

                                                echo $meteo[$cont]["weatherSymbolValue"];
                                            ?>
                                            </p>
                                        </div>
                                        <div style="float: left; height: 100%; width: 30%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Temp Max
                                            </p>
                                            <?php
                                                $temp = $meteo[$cont]["tempMax"];
                                                if($temp > 0 && $temp <= 15)
                                                    echo '<p class="fg-lightBlue"';
                                                else if ($temp > 15 && $temp <= 25)
                                                    echo '<p class="fg-yellow"';
                                                else if($temp > 25)
                                                    echo '<p class="fg-amber"';
                                                else
                                                    echo '<p class="fg-blue"';

                                                echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                                echo $temp . '°C';
                                            ?>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="live-slide" style="top: 150px; display: block;">
                                <div style="background-color: #3c3f41; display: block;" class="tile-content fg-white">
                                    <div style="float: left; height: 20%; width: 100%; border-bottom: 1px solid rgba(255, 255, 255, 1);">
                                        <h3 style="margin: 0 0 0 0; height: 100%;"><?php echo strtoupper($meteo[$cont]["dayName"]); ?></h3>
                                    </div>
                                    <div style="float: left; top: 20%; height: 80%; width: 100%; display: block;">
                                        <div style="float: left; height: 100%; width: 30%; text-align: center; border-right: 1px solid rgba(255, 255, 255, 0.5);">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Temp Min
                                            </p>
                                            <?php
                                                $temp = $meteo[$cont]["tempMin"];
                                                if($temp > 0 && $temp <= 15)
                                                    echo '<p class="fg-lightBlue"';
                                                else if ($temp > 15 && $temp <= 25)
                                                    echo '<p class="fg-yellow"';
                                                else if($temp > 25)
                                                    echo '<p class="fg-amber"';
                                                else
                                                    echo '<p class="fg-blue"';

                                                echo ' style="position: relative; height: auto; line-height: 120%; font-size: xx-large;">';
                                                echo $temp . '°C';
                                            ?>
                                        </div>
                                        <div style="float: left; height: 100%; width: 70%; text-align: center;">
                                            <p style="position: relative; height: 20%; line-height: 120%; font-size: large; margin: 10px 0 0 0; border-bottom: 1px solid rgba(255, 255, 255, 0.2);">
                                                Descrizione Vento
                                            </p>
                                            <?php
                                                $length = strlen($meteo[$cont]["windSymbolValue"]);
                                                if($length <= 105)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: large; margin-left: 5%; margin-right: 5%;">';
                                                else if($length > 100 && $length < 150)
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: medium; margin-left: 5%; margin-right: 5%;">';
                                                else
                                                    echo '<p style="position: relative; height: auto; line-height: 120%; font-size: small; margin-left: 5%; margin-right: 5%;">';

                                                echo $meteo[$cont]["windSymbolValue"];
                                            ?>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <?php
                $cont++;
                $num++;
                }
            }
            else
                echo '<h1>Clicca su "Live" per aggiornare la pagina...</h1>';
            ?>
        </div>
    </body>
</html>
