<%-- 
    Document   : insertReview
    Created on : 10-ott-2017, 22.13.47
    Author     : andreafadi
--%>
<style>
    div.stars {
        width: auto;
        display: inline-block;
    }

    input.star {display: none; }
    label { top: 0; margin: 0; padding: 0;}

    label.star {
        float: right;
        padding: 10px;
        font-size: 50px;
        color: #444;
        transition: all .2s;
    }

    input.star:checked ~ label.star:before {
        content: '\f005';
        color: #FD4;
        transition: all .25s;
    }

    input.star-5:checked ~ label.star:before {
        color: #FE7;
        text-shadow: 0 0 20px #952;
    }

    input.star-1:checked ~ label.star:before { color: #F62; }
    label.star:hover { transform: rotate(-15deg) scale(1.3); }

    label.star:before {
        content: '\f006';
        font-family: FontAwesome;
    }
    
    @media screen and (max-width: 480px) {
        label.star {
            padding: 2px;
            font-size: 36px;
        }
    }
    
    .form-control-file, .form-control-range {
        display: inline-block;
    }
    
    input {
        margin: 0;
    }
    
    .distance {
        margin-bottom: 10px;
        display: block;
    }
</style>

<div class="text-center">
    <h2>Lascia una Recensione!</h2>
    <form id="formReview" name="formReview" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/InserisciRecensioneController?action=Venditore">
        <div class="form-group">
            <textarea class="form-control" id="testoReview" name="testoReview" rows="3" placeholder="Scrivi qui la tua recensione..."></textarea>
        </div>
        <div class="form-group">
            <div class="stars">
                <input class="star star-5" id="star-5" type="radio" name="valutazioneReview" value="5"/>
                <label class="star star-5" for="star-5"></label>
                <input class="star star-4" id="star-4" type="radio" name="valutazioneReview" value="4"/>
                <label class="star star-4" for="star-4"></label>
                <input class="star star-3" id="star-3" type="radio" name="valutazioneReview" value="3"/>
                <label class="star star-3" for="star-3"></label>
                <input class="star star-2" id="star-2" type="radio" name="valutazioneReview" value="2"/>
                <label class="star star-2" for="star-2"></label>
                <input class="star star-1" id="star-1" type="radio" name="valutazioneReview" value="1"/>
                <label class="star star-1" for="star-1"></label>
            </div>
        </div>
        <c:if test="${canUploadImages == true}">
            <div class="form-group">
                <label class="distance" for="file">Carica delle immagini per una recensione migliore!</label>
                <input type="file" class="form-control-file" id="file" name="file" accept="image/*" multiple>
            </div>
        </c:if>
        <input type="hidden" id="utenteReview" name="utenteReview" value="${utenteSessione.getId()}"/>
        <input type="hidden" id="venditoreReview" name="venditoreReview" value="${venditore.getId()}"/>
        <input type="submit" class="btn btn-primary" value="Inserisci"/>
    </form>
</div>