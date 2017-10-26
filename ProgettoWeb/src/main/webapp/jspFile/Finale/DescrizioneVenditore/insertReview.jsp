<%-- 
    Document   : insertReview
    Created on : 10-ott-2017, 22.13.47
    Author     : andreafadi
--%>

<div style="display: flex; justify-content: center; align-items: center;">
    <form id="formReview" name="formReview" method="POST" action="${pageContext.request.contextPath}/InserisciRecensioneController?action=Venditore">
        <div class="form-group">
            <textarea class="form-control" id="testoReview" name="testoReview" rows="3" placeholder="Scrivi qui la tua recensione..."></textarea>
        </div>
        <div class="form-group">
            <select class="form-control" id="valutazioneReview" name="valutazioneReview">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
        <div class="form-group">
            <input type="file" class="form-control-file" id="fotoReview" name="fotoReview" aria-describedby="fileHelp">
            <small id="fileHelp" class="form-text text-muted">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small>
        </div>
        <input type="hidden" id="utenteReview" name="utenteReview" value="${utenteSessione.getId()}"/>
        <input type="hidden" id="oggettoReview" name="venditoreReview" value="${venditore.getId()}"/>
        <input type="submit" class="btn btn-primary" value="Inserisci"/>
    </form>
</div>