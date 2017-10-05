<%-- 
    Document   : slideShow
    Created on : 28-set-2017, 16.00.01
    Author     : mattia
--%>

<div class="carouselSpacingDeveloper">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img class="d-block w-100" src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/developer1.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption d-none d-md-block text-left">
              <h1>Lorem ipsum</h1>
              <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam</p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/developer2.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption d-none d-md-block text-right">
              <h1>Lorem ipsum</h1>
              <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam</p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/developer3.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption d-none d-md-block text-top">
              <h1>Lorem ipsum</h1>
              <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam</p>
            </div>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
</div>
