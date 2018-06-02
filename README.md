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


