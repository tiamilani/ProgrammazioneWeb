<%--
    Document   : homeHead
    Created on : 30-set-2017, 10.04.54
    Author     : mattia
--%>

    <link href="https://fonts.googleapis.com/css?family=Cookie" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel='stylesheet' href="http://getbootstrap.com/docs/4.0/examples/carousel/carousel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/navBar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/slideShow.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/oggetto.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/filtri.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/filtriLargeContainer.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.16/cr-1.4.1/r-2.2.1/rg-1.0.2/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.16/cr-1.4.1/r-2.2.1/rg-1.0.2/datatables.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/ProgettoWeb/jspFile/Finale/JS/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/btnColor.css" >
    <script src="${pageContext.request.contextPath}/jspFile/Finale/JS/md5.min.js"></script>
<script>
    $(document).ready(function(){
      $('#registerModal input').focus(function(){
          $(this).next('label').addClass('moveUp');
          $(this).prev('i').addClass('color');
      });
    });

    $(document).ready(function(){
      $('#registerModal input').on('focusout', function() {
          if(!$(this).val()) $(this).next('label').removeClass('moveUp');
          if(!$(this).val()) $(this).prev('i').removeClass('color');
      });
    });

    $(document).ready(function(){
      $('#loginModal input').focus(function(){
          $(this).next('label').addClass('moveUp');
          $(this).prev('i').addClass('color');
      });
    });

    $(document).ready(function(){
      $('#loginModal input').on('focusout', function() {
          if(!$(this).val()) $(this).next('label').removeClass('moveUp');
          if(!$(this).val()) $(this).prev('i').removeClass('color');
      });
    });

    $(document).ready(function(){
      $('#passwordModal input').focus(function(){
          $(this).next('label').addClass('moveUp');
          $(this).prev('i').addClass('color');
      });
    });

    $(document).ready(function(){
      $('#passwordModal input').on('focusout', function() {
          if(!$(this).val()) $(this).next('label').removeClass('moveUp');
          if(!$(this).val()) $(this).prev('i').removeClass('color');
      });
    });

    $(document).ready(function(){
      $('.text-input').focus(function(){
          $(this).next('label').addClass('moveUp');
      });
    });

    $(document).ready(function(){
      $('.text-input').on('focusout', function() {
          if(!$(this).val()) $(this).next('label').removeClass('moveUp');
      });
    });
</script>

<style>
    body{
        padding-bottom: 0;
    }
</style>
