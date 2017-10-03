<%-- 
    Document   : homeHead
    Created on : 30-set-2017, 10.04.54
    Author     : mattia
--%>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel='stylesheet' href="http://getbootstrap.com/docs/4.0/examples/carousel/carousel.css">
    <link rel="stylesheet" href="../CSS/navBar.css">
    <link rel="stylesheet" href="../CSS/slideShow.css">
    <link rel="stylesheet" href="../CSS/oggetto.css">
    <link rel="stylesheet" href="../CSS/filtri.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

    <script>
      $(document).ready(function(){
        $('#registerModal input').focus(function(){
            $(this).next('label').addClass('moveUp');
        });
      });

      $(document).ready(function(){
        $('#registerModal input').on('focusout', function() {
            if(!$(this).val()) $(this).next('label').removeClass('moveUp');
        });
      });
</script>
