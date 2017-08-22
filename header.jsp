<!--
   HEADER
   Header frontale per il sito, versione html/jsp senza css
   il jsp per la ricerca non è stato ancora implementato
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<body>
		<h1>HELLO WORLD I'M THE HEADER, LOGO: ":)"</h1>
		
		<h3 style="border-bottom: 1px solid #dddddd;">parte relativa al login:</h3>
		<form>
			<label class="textLabel" for="ititle">Username</label>          
			<input type="text" class="form-control" id="txtUsername" placeholder="">
			
			<br>
			<label class="textLabel" for="ititle">Password</label>          
			<input type="password" class="form-control" id="txtPassword" placeholder="">
			<br>
			<button type="submit" class="btn btn-primary">Login</button>
			<button type="submit" class="btn btn-primary">Registrati</button>
		</form>
		<h1 style="border-bottom: 1px solid #dddddd;"></h1>
		
		<h3 style="border-bottom: 1px solid #dddddd;">parte relativa alla ricerca di un oggetto con filtri:</h3>
		</form>
			<label class="textLabel" for="ititle">Barra di ricerca:</label>          
			<input type="text" class="form-control" id="txtRicerca" placeholder="">
			<button type="submit" class="btn btn-primary">Cerca</button>
			<br>
			
			<p style="bold">Filtri</p>
			filtro per ottenere una ricerca negli oggetti di un negozio specifico<br>
			<label class="textLabel" for="ititle">Nome negozio:</label>          
			<input type="text" class="form-control" id="txtNomeNegozio" placeholder=""><br><br>
			
			filtro per ottenere una ricerca negli oggetti di un venditore specifico<br>
			<label class="textLabel" for="ititle">Nome Venditore:</label>          
			<input type="text" class="form-control" id="txtNomeVenditore" placeholder=""><br><br>
			
			una slidebar per selezionare il limite di prezzo, slidebar con due pulsanti, uno che segna il minimo ed uno il massimo<br>
			<input type="range" min="0" max="1000" value="0" onchange="showValue(this.value)"/>
			<span id="range">0</span> euro<br><br>
			
			una slidebar con un solo pallino di per selezionare la distanza in km da dove si è attualmente<br>
			<input type="range" min="0" max="1000" value="0" onchange="showValue(this.value)"/>
			<span id="range">0</span>Km<br><br>
			
			come opzione aggiuntiva si può selezionare una città come centro della ricerca invece del rilevamento automatico<br>
			<label class="textLabel" for="ititle">Nome Città:</label>          
			<input type="text" class="form-control" id="txtNomeCitta" placeholder=""><br><br>
			
			listbox in cui selezionare una categoria <br>
			<select id="selectCategoria" class="form-control">
				<option value="C" checked>Categoria</option>
				<option value="c1">categoria1</option>
				<option value="c2">categoria1</option>
				<option value="c3">categoria1</option>
			</select>
        </form>
		<h1 style="border-bottom: 1px solid #dddddd;"></h1>
		
		<h3 style="border-bottom: 1px solid #dddddd;">parte relativa ai pulsanti che un utente può premere: </h3>
		(Oltre a quelli già presenti, login/registrati/cerca)<br>

		<button type="submit" class="btn btn-primary">Carrello</button>
		<button type="submit" class="btn btn-primary">Assistenza</button>
		<button type="submit" class="btn btn-primary">Profilo</button>
		<button type="submit" class="btn btn-primary">Campanella</button>
		<button type="submit" class="btn btn-primary">Le mie statistiche (Venditore)</button>
		<button type="submit" class="btn btn-primary">I miei prodotti/negozi (Venditore)</button>
		<button type="submit" class="btn btn-primary">Incarichi (Amministratore)</button>

		<h1 style="border-bottom: 1px solid #dddddd;"></h1>
		
		<script type="text/javascript">
			function showValue(newValue)
			{
				document.getElementById("range").innerHTML=newValue;
			}
		</script>
	</body>
</html>
