Project Checkpoint: ![Checkpoint PDF](checkpoint.pdf)

UML:

![alt text](UML.png)



	- Setup/Installation procedure
	
		- Desktop
	
		- Java
	
	
	
	
	
	
	
	- Development documentation
	
		- UML
		
		
		
		- Design Patterns Used
			
			Us�mos Model/View/Controller (MVC) para a arquitetura base do nosso projeto.
	
			Us�mos um Singleton nas classes GameController e GameModel visto que apenas quer�amos 1 inst�ncia do jogo.
	
			Us�mos um Observer para tratar colis�es dos nossos Model (visto que estamos a usar o Box2d) sem que os diferentes objetos se tornassem fortemente acoplados.
	
			Us�mos um Flyweigth para poupar mem�ria, visto termos objetos que partilham os seus sprites correspondentes, apenas sofrendo modifica��es de dire��o em fun��o da dire��o do Model.
	
			Us�mos um Object Pool para os proj�teis do Hero.
	
			Inicialmente �amos usar um State para gerir os v�rios estados do nosso Hero em fun��o dos diferentes inputs, mas ach�mos que 1 classe por cada estado era exagero, visto apenas termos cerca de 6 estados e as suas transi��es eram pouco complexas, por isso decidimos usar um switch na fun��o de move (tanto do Hero como da Slug) do estado atual, em que cada case incluiria poss�veis transi��es para outros estados.
			
			Us�mos uma Factory para (... JUAN HELP!)
		
		
		
		- Major Dificulties
		
		Tivemos m�ltiplas dificuldades na realiza��o deste projeto, como seria de esperar. Uma das mais s�rias foi a falta de tempo. No final deste semestre tivemos m�ltiplas entregas consecutivasde projetos de diferentes cadeiras, o que diminuiu bastante o tempo que pudemos dedicar a este projeto. Al�m disso, os exames come�am dias depois da entrega, o que diminuiu ainda mais o tempo dispon�vel. Devido a esta primeira dificuldade, rapidamente se tornou �bvio que t�nhamos outro problema, fomos demasiado ambiciosos. Quer�amos ter implementado mais 2 levels ao jogo, e mais 3 ou 4 enemies com AI diferentes, mas o tempo simplesmente n�o chegou para tudo. Conseguimos no entanto implementar funcionalidade de multijogador, algo que n�o estavamos � espera de conseguir realizar.
		Al�m disto, n�o fomos capazes de implementar testes unit�rios ao package Game.Controller devido a problemas com a biblioteca libGDX. No entanto realizamos testes na package Game.Model, com resultados (...)
		
		
		
		- Lessons Learned
		
		Aprendemos que devemos ser menos ambiciosos nestes projetos, porque o tempo nem sempre est� do nosso lado e podemos acabar por prometer mais do que conseguimos cumprir.
		No entanto ambos achamos que este projeto nos ajudou bastante a compreender melhor design de jogos (e aplica��es em geral), pre-planear a estrutura levou a que tiv�ssemos muito menos casos de "c�digo esparguete" a tentar disfa�ar erros no nosso design. A estrutura MVC provou ser bastante �til neste aspeto, sab�amos sempre onde dev�amos colocar uma dada fun��o, dependendo da sua funcionalidade.
		
		
		
		- Overall time spent developing
		
		Estimamos ter gasto cerca de 220 horas na realiza��o deste projeto
		
		
		
		- Work distribution amongst team members
		
		Ambos os elementos trabalharam de igual forma na realiza��o deste projeto
		
	
	
	
	- User manual
	
		O primeiro passo � iniciar o programa/aplica��o (descri��o de como fazer isto encontra-se na sec��o "Setup/Installation procedure" acima.
		
		Assumindo que a aplica��o est� ligada, ir� ver o seguinte menu inicial:
			
![alt text](screenshots/StartMenu.png)

		Clicando em "Singleplayer" ir� iniciar o jogo em modo de jogador �nico. Em desktop para controlar o Hero, ter� de usar as setas do teclado, e a tecla "A" para atacar (lan�ar bolas plasma). Em Android, ter� no ecr� 4 bot�es como podemos ver na imagem seguinte:
		
![alt text](screenshots/game_over_background.png)

		Os bot�es realizam as seguintes a��es (listando da esquerda para a direita): andar para esquerda, andar para a direita, saltar e atacar.
		
		
		O objetivo do jogo � chegar ao final do n�vel sem morrer. O Hero morre se cair num po�o ou se levar dano suficiente dos enemies.
		
		A parte inicial do n�vel serve para famialirizar o Hero com as mec�nicas de jogo, como os controlos, a velocidade do Hero, a altura dos seus saltos, etc... Por este motivo n�o existem enemies nesta sec��o.
		Ap�s passar esta parte do n�vel, come�am a aparecer os enemies. A Slug tem como comportamento normal percorrer uma dada �rea do n�vel de forma c�clica. No entanto se algum Hero entrar no seu campo de vis�o, ela ir� segui-lo indefinitamente, s� voltando ao seu comportamento inicial se deixar de "ver" o Hero. Se a Slug tiver seguido o Hero para longe da sua zona de "roaming", e deixar de ver o Hero, ent�o ir� voltar a essa zona e retomar o comportamento normal.
		A Slug sofre dano quando entra em contacto com uma Plasma Ball de um Hero, e causa dano se estiver pr�xima o suficiente do Hero.

		No caso do Hero ganhar o jogo, o seguinte ecr� surge:
		
![alt text](android/assets/game_won_background.png)		
		
		E no caso de ficar sem HP ou cair num pit, o seguinte ecr� surge:
		
![alt text](android/assets/game_over_background.png)			



		Voltando ao menu principal, clicando em "Multiplayer" ir� abrir um segundo menu que pede para especificar se o utilizador pretende dar "Host" a um jogo, ou se pretende procurar um servidor para jogar.
		
![alt text](screenshots/MultiplayerMenu.png)		 
				
		Se o utilizador escolher "Host", ent�o entrar� no jogo e esperar� que surja um Player 2. Enquanto espera aparece uma janela que mostra o IP que o Player 2 ter� de inserir para entrar no jogo, e um bot�o de cancelar que retorna ao menu principal caso o utilizador n�o queira esperar mais:
		
![alt text](screenshots/HostMenu.png)	

		Se o utilizador escolher "Find", ent�o ir� aparecer uma janela a pedir o IP do servidor a que se	quer conectar:
		
![alt text](screenshots/FindMenu.png)

		
		A forma de jogador entre 1 jogador e 2 jogadores � a mesma. No entanto, o ecr� apenas � desenhado no lado do Host, o Player 2 apenas v� os bot�es de controlo no seu ecr�.
		
		Ecr� Player 1:			
		
![alt text](screenshots/game_over_background.png)		
		
		Ecr� Player 2:
		
![alt text](screenshots/Player2screen.png)


		

