<%-- 
    Document   : oggetto
    Created on : 28-set-2017, 16.36.53
    Author     : mattia
--%>
<style>
    div.user-votes div.stars {
        width: auto;
        display: inline-block;
        margin: auto;
    }
    div.user-votes input.star {display: none; }
    div.user-votes label { top: 0; margin: 0; padding: 0;}
    div.user-votes label.star {
        float: right;
        padding: 5px;
        font-size: 20px;
        color: #444;
        transition: all .2s;
    }
    div.user-votes input.star:checked ~ label.star:before {
        content: '\f005';
        color: #FD4;
        transition: all .25s;
    }
    div.user-votes input.star-5:checked ~ label.star:before {
        color: #FE7;
        text-shadow: 0 0 20px #952;
    }
    div.user-votes input.star-1:checked ~ label.star:before { color: #F62; }
    div.user-votes label.star:hover { transform: rotate(-15deg) scale(1.3); }
    div.user-votes label.star:before {
        content: '\f006';
        font-family: FontAwesome;
    }
    
    @media screen and (max-width: 480px) {
        label.star {
            padding: 2px;
            font-size: 36px;
        }
    }
    
    div.user-votes .form-control-file, .form-control-range {
        display: inline-block;
    }
    
    div.user-votes input {
        margin: 0;
    }
    
    div.user-votes .distance {
        margin-bottom: 10px;
        display: block;
    }
    
    div.user-votes .checkbox label:after, 
    div.user-votes .radio label:after {
        content: '';
        display: table;
        clear: both;
    }

    div.user-votes .checkbox .cr,
    div.user-votes .radio .cr {
        position: relative;
        display: inline-block;
        border: 1px solid #a9a9a9;
        border-radius: .25em;
        width: 1.3em;
        height: 1.3em;
        float: left;
        margin-right: .5em;
    }

    div.user-votes .radio .cr {
        border-radius: 50%;
    }

    div.user-votes .checkbox .cr .cr-icon,
    div.user-votes .radio .cr .cr-icon {
        position: absolute;
        font-size: .8em;
        line-height: 0;
        top: 50%;
        left: 20%;
    }

    div.user-votes .radio .cr .cr-icon {
        margin-left: 0.04em;
    }

    div.user-votes .checkbox label input[type="checkbox"],
    div.user-votes .radio label input[type="radio"] {
        display: none;
    }

    div.user-votes .checkbox label input[type="checkbox"] + .cr > .cr-icon,
    div.user-votes .radio label input[type="radio"] + .cr > .cr-icon {
        transform: scale(3) rotateZ(-20deg);
        opacity: 0;
        transition: all .3s ease-in;
    }

    div.user-votes .checkbox label input[type="checkbox"]:checked + .cr > .cr-icon,
    div.user-votes .radio label input[type="radio"]:checked + .cr > .cr-icon {
        transform: scale(1) rotateZ(0deg);
        opacity: 1;
    }

    div.user-votes .checkbox label input[type="checkbox"]:disabled + .cr,
    div.user-votes .radio label input[type="radio"]:disabled + .cr {
        opacity: .5;
    }
    
    
</style>


<c:url value="/objectSelectedController" var="objUrl" >
    <c:param name="idOggetto" value="${listaOggetti.get(iterator).getId()}" />
</c:url>
<div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
    <div class="card card-present">    
        <a href="${objUrl}" style="box-shadow: none;">
            <img class="card-img-top" src="${listaImmaginiOggetto.get(iterator).getSrc()}" alt="IMAGE NOT LOADED" style="height: 250px; object-fit: scale-down;"> <!--add object-fit: contain;-->
        </a>

        <div class="card-block">
            <span class="product-price"> <c:out value="${listaOggetti.get(iterator).getPrezzo()}" />&euro; </span>  
            <span class="card-title"> 
                <a href="${objUrl}" style="box-shadow: none;"> <c:out value="${listaOggetti.get(iterator).getNome()}" /> </a>
            </span>   
        </div>

        <div class="overlay">
            <div class="row no-gutters">
                <p class="card-text col-12"> <c:out value="${listaOggetti.get(iterator).getDescrizione()}" /> </p>
            </div>
            
            <div class="valutazione row no-gutters justify-content-between">
                <div class="user-votes col-8">
                    <div class="stars-${listaOggetti.get(iterator).getId()}">
                        <input class="star star-5" id="star-5-${listaOggetti.get(iterator).getId()}" type="radio" name="valutazioneReview-${listaOggetti.get(iterator).getId()}" value="5" disabled ${iterator == 5 ? 'checked' : ''}/>
                        <label class="star star-5" for="star-5-${listaOggetti.get(iterator).getId()}"></label>
                        <input class="star star-4" id="star-4-${listaOggetti.get(iterator).getId()}" type="radio" name="valutazioneReview-${listaOggetti.get(iterator).getId()}" value="4" disabled ${iterator == 4 ? 'checked' : ''}/>
                        <label class="star star-4" for="star-4-${listaOggetti.get(iterator).getId()}"></label>
                        <input class="star star-3" id="star-3-${listaOggetti.get(iterator).getId()}" type="radio" name="valutazioneReview-${listaOggetti.get(iterator).getId()}" value="3" disabled ${iterator == 3 ? 'checked' : ''}/>
                        <label class="star star-3" for="star-3-${listaOggetti.get(iterator).getId()}"></label>
                        <input class="star star-2" id="star-2-${listaOggetti.get(iterator).getId()}" type="radio" name="valutazioneReview-${listaOggetti.get(iterator).getId()}" value="2" disabled ${iterator == 2 ? 'checked' : ''}/>
                        <label class="star star-2" for="star-2-${listaOggetti.get(iterator).getId()}"></label>
                        <input class="star star-1" id="star-1-${listaOggetti.get(iterator).getId()}" type="radio" name="valutazioneReview-${listaOggetti.get(iterator).getId()}" value="1" disabled ${iterator == 1 ? 'checked' : ''}/>
                        <label class="star star-1" for="star-1-${listaOggetti.get(iterator).getId()}"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
