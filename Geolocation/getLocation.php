<?php

function foreachContents($link, $compare)
{
    $xml = simplexml_load_file($link);

    foreach ($xml->location->data as $var)
    {
        $i = 0;
        if($var[$i]->name == $compare)
            return $var[$i]->name->attributes()->id;
        $i++;
    }
}

if(!empty($_POST['latitude']) && !empty($_POST['longitude']))
{
    //Send request and receive json data by latitude and longitude
    $url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='.trim($_POST['latitude']).','.trim($_POST['longitude']).'&sensor=false';
    $json = @file_get_contents($url);
    $data = json_decode($json);
    $status = $data->status;

    if($status == "OK")
    {
        $province = $data->results[0]->address_components[3]->long_name;
        $city = $data->results[0]->address_components[2]->long_name;
        $location = $data->results[0]->formatted_address;

        $provinceId = foreachContents("http://api.ilmeteo.net/index.php?api_lang=it&pais=20&affiliate_id=4z7c7es7kdqr", $province);
        $cityId = foreachContents("http://api.ilmeteo.net/index.php?api_lang=it&provincia=" . $provinceId . "&affiliate_id=4z7c7es7kdqr", $city);

        if(empty($cityId))
            $cityId = foreachContents("http://api.ilmeteo.net/index.php?api_lang=it&provincia=" . $provinceId . "&affiliate_id=4z7c7es7kdqr", $province);

        setcookie("cityId", $cityId, time() + 43200, "/");
    }
    else
        $location =  '';

    //Print address
    echo $location;
}
?>
