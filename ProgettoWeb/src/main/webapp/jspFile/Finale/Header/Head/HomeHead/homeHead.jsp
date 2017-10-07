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
    <link rel="stylesheet" href="../CSS/footer.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

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

    $(document).ready(function(){
      $('#loginModal input').focus(function(){
          $(this).next('label').addClass('moveUp');
      });
    });

    $(document).ready(function(){
      $('#loginModal input').on('focusout', function() {
          if(!$(this).val()) $(this).next('label').removeClass('moveUp');
      });
    });
</script>


