Project Checkpoint: ![Checkpoint PDF](checkpoint.pdf)

UML:

![alt text](UML.png)

	- Setup/Installation procedure

		- Desktop
   
		- Java
   
   



   
   
	- Development documentation
 
		- UML
		
		
		
		- Design Patterns Used
			
			Usámos Model/View/Controller (MVC) para a arquitetura base do nosso projeto.

			Usámos um Singleton nas classes GameController e GameModel visto que apenas queríamos 1 instância do jogo.

			Usámos um Observer para tratar colisões dos nossos Model (visto que estamos a usar o Box2d) sem que os diferentes objetos se tornassem fortemente acoplados.

			Usámos um Flyweigth para poupar memória, visto termos objetos que partilham os seus sprites correspondentes, apenas sofrendo modificações de direção em função da direção do Model.

			Usámos um Object Pool para os projéteis do Hero.

			Inicialmente íamos usar um State para gerir os vários estados do nosso Hero em função dos diferentes inputs, mas achámos que 1 classe por cada estado era exagero, visto apenas termos cerca de 6 estados e as suas transições eram pouco complexas, por isso decidimos usar um switch na função de move (tanto do Hero como da Slug) do estado atual, em que cada case incluiria possíveis transições para outros estados.
			
			Usámos uma Factory para (... JUAN HELP!)
		
		
		
		- Major Dificulties
		
		Tivemos múltiplas dificuldades na realização deste projeto, como seria de esperar. Uma das mais sérias foi a falta de tempo. No final deste semestre tivemos múltiplas entregas consecutivasde projetos de diferentes cadeiras, o que diminuiu bastante o tempo que pudemos dedicar a este projeto. Além disso, os exames começam dias depois da entrega, o que diminuiu ainda mais o tempo disponível. Devido a esta primeira dificuldade, rapidamente se tornou óbvio que tínhamos outro problema, fomos demasiado ambiciosos. Queríamos ter implementado mais 2 levels ao jogo, e mais 3 ou 4 enemies com AI diferentes, mas o tempo simplesmente não chegou para tudo. Conseguimos no entanto implementar funcionalidade de multijogador, algo que não estavamos à espera de conseguir realizar.
		Além disto, não fomos capazes de implementar testes unitários ao package Game.Controller devido a problemas com a biblioteca libGDX. No entanto realizamos testes na package Game.Model, com resultados (...)
		
		
		
		- Lessons Learned
		
		Aprendemos que devemos ser menos ambiciosos nestes projetos, porque o tempo nem sempre está do nosso lado e podemos acabar por prometer mais do que conseguimos cumprir.
		No entanto ambos achamos que este projeto nos ajudou bastante a compreender melhor design de jogos (e aplicações em geral), pre-planear a estrutura levou a que tivéssemos muito menos casos de "código esparguete" a tentar disfaçar erros no nosso design. A estrutura MVC provou ser bastante útil neste aspeto, sabíamos sempre onde devíamos colocar uma dada função, dependendo da sua funcionalidade.
		
		
		
		- Overall time spent developing
		
		Estimamos ter gasto cerca de 220 horas na realização deste projeto
		
		
		
		- Work distribution amongst team members
		
		Ambos os elementos trabalharam de igual forma na realização deste projeto
		



	- User manual
	
		O primeiro passo é iniciar o programa/aplicação (descrição de como fazer isto encontra-se na secção "Setup/Installation procedure" acima.
		
		Assumindo que a aplicação está ligada, irá ver o seguinte menu inicial:
		
![alt text](https://github.com/Raidenkyu/LPOO1718_T3G5-FinalProj/blob/master/android/assets/game_over_background.png)
	
		test123
		
![alt text](android/assets/game_over_background.png)
		

