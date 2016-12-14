<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="mtag" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Compliance Software - Desenvolvendo Aplicativos Inteligentes!</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="robots" content="index, follow, noarchive">
  <meta name="descrption" content="Transformamos sua ideia em projeto e garantimos o sucesso de seu empreendimento dentro do mundo digital. Desenvolvemos sites, softwares e aplicativos para Smartphones.">
  <mtag:cssJsHead/>
  <mtag:favicon/>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#myPage"><mtag:logoImage/></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#about">Sobre nós</a></li>
        <li><a href="#services">O que fazemos</a></li>
        <!--li><a href="#portfolio">Portifólio</a></li -->
        <li><a href="#contact">Contato</a></li>
      </ul>
    </div>
  </div>
</nav>
<br>
<br>
<div class="jumbotron text-center">
  <h1>Compliance Software</h1> 
  <p>Somos especializados em trazer o futuro pra perto de você!</p> 
  <form class="form-inline" action="inscrever" method="post">
    <input id="subsemail" name="subsemail" type="email" class="form-control" size="50" placeholder="Informe seu e-mail" required>
    <button type="submit" class="btn btn-info">Inscreva-se!</button>
    <mtag:mensagem msg="${mensagem}"/>
  </form>
</div>

<!-- Container (About Section) -->
<div id="about" class="container-fluid">
  <div class="row">
    <div class="col-sm-8">
      <h2>Sobre nós</h2><br>
      <h4>A Compliance Software nasceu do desejo de ajudar aos empreendedores que apostam em seus projetos e fazem deles suas metas de vida.</h4><br>
      <p>Após longo tempo de pesquisa, percebemos a necessidade do mercado de estar sempre atualizado e compatível com o desejo mais individual das pessoas. 
      Atualmente, o mercado digital virou a porta de entrada dos consumidores de qualquer segmento, porém, nem todos os empreendedores têm acesso facilitado a este mercado moderno. 
      Temos orgulho de acreditar em você e na sua capacidade de crescer e ganhar sucesso em seu trabalho. 
      Por isso, estamos aqui para ajuda-lo oferecendo o que há de mais moderno e sofisticado no mercado digital. Afinal...<br>
      "Desenvolver sua ideia é nossa satisfação!" - <small>(Douglas Fernandes - Compliance Software CEO)</small>
      </p>
      <br><a href="#contact"><button class="btn btn-default btn-lg">Entre em contato</button></a>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-signal logo"></span>
    </div>
  </div>
</div>

<div class="container-fluid bg-grey">
  <div class="row">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-globe logo slideanim"></span>
    </div>
    <div class="col-sm-8">
      <h2>Nossos Valores</h2><br>
      <h4><strong>Missão:</strong> <span lang="pt-br">Nossa missão é incluir 
	  você, de maneira sutil e objetiva, no mercado digital, suprindo seu 
	  empreendimento com sistemas, sites, webapps e aplicativos móveis que farão 
	  sua empresa disparar no comércio com destaque que somente empresas de 
	  grande porte conseguem atualmente.</span></h4><br>
      <p><strong><span lang="pt-br">Visão</span>:</strong> <span lang="pt-br">
	  Acreditamos que o funcionamento do mercado mudou com as novidades 
	  tecnológicas de forma a garantir espaço inclusive pra quem nem conhecia a 
	  tecnologia como ferramenta. Trazemos a tecnologia de forma didática e 
	  objetiva a ponto de tornar sua empresa mais moderna e você cada vez mais 
	  informado das tendências deste mundo.<br>Você é mais que cliente... é nosso <strong>amigo</strong>!</span></p>
    </div>
  </div>
</div>

<!-- Container (Services Section) -->
<div id="services" class="container-fluid text-center">
  <h2>O que fazemos</h2>
  <h4>Nossos produtos</h4>
  <br>
  <div class="row slideanim">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-folder-open logo-small"></span>
      <h4>Sistemas para computadores (Windows, Linux e MAC)</h4>
      <p>Softwares diversos e personalizados com o que você mais precisa para agilizar seu dia-a-dia em seu negócio.</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-fullscreen logo-small"></span>
      <h4>Sites responsivos</h4>
      <p>Oferecemos o que há de mais moderno em desenvolvimento de sites adaptáveis a todo dispositivo que suporte HTML e/ou HTML5.</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-link logo-small"></span>
      <h4>Web Apps</h4>
      <p>Aplicativos web que trabalham em conjunto com seu site dando a vantagem de sempre o deixar atualizado com o que você tem de mais novo a oferecer a seus clientes.</p>
    </div>
  </div>
  <br><br>
  <div class="row slideanim">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-phone logo-small"></span>
      <h4>Mobile Apps</h4>
      <p>Aplicativos para Android e IOS com o mais moderno em design e altamente personalizados para o seu negócio.</p>
    </div>
  </div>
</div>

<!-- Container (Portfolio Section) -->
<!-- div id="portfolio" class="container-fluid text-center bg-grey">
  <h2>Portfolio</h2><br>
  <h4>What we have created</h4>
  <div class="row text-center slideanim">
    <div class="col-sm-4">
      <div class="thumbnail">
        <img src="paris.jpg" alt="Paris" width="400" height="300">
        <p><strong>Paris</strong></p>
        <p>Yes, we built Paris</p>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="thumbnail">
        <img src="newyork.jpg" alt="New York" width="400" height="300">
        <p><strong>New York</strong></p>
        <p>We built New York</p>
      </div>
    </div>
    <div class="col-sm-4">
      <div class="thumbnail">
        <img src="sanfran.jpg" alt="San Francisco" width="400" height="300">
        <p><strong>San Francisco</strong></p>
        <p>Yes, San Fran is ours</p>
      </div>
    </div>
  </div --><br>
  
  <div id="myCarousel" class="carousel slide text-center" data-ride="carousel">
    <h2>O que nossos amigos dizem</h2>
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <h4>"Eu nem sabia o que significava "Delivery" e agora meus clientes pedem pelo meu aplicativo!"<br><span style="font-style:normal;">Christiano Souza, Empreendedor, Individual</span></h4>
      </div>
      <div class="item">
        <h4>"Agora tô ligado!"<br><span style="font-style:normal;">Roberval Andrade, Sócio Proprietário e Diretor, Imobiliária Uniméveis Pernambuco</span></h4>
      </div>
      <div class="item">
        <h4>"Simples e sofisticado... Do jeito que eu quis!"<br><span style="font-style:normal;">Edgar Diniz, Poeta Empreendedor, Individual</span></h4>
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Anterior</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Próximo</span>
    </a>
  </div>

<!-- Container (Contact Section) -->
<div id="contact" class="container-fluid bg-grey">
  <h2 class="text-center">Contato</h2>
  <div class="row">
    <div class="col-sm-5">
      <p>Entre em contato e responderemos em breve.</p>
      <p><span class="glyphicon glyphicon-map-marker"></span> Pernambuco, Brasil</p>
      <p><span class="glyphicon glyphicon-phone"></span> +55 81 9 8832-1842</p>
      <p><span class="glyphicon glyphicon-envelope"></span> contato.compliancesoftware@gmail.com</p>
    </div>
    <div class="col-sm-7 slideanim">
    	<form class="form-horizontal" action="enviaEmail" method="post">
      		<div class="row">
        		<div class="col-sm-6 form-group">
          			<input class="form-control" id="name" name="name" placeholder="Nome" type="text" required>
        		</div>
        		<div class="col-sm-6 form-group">
          			<input class="form-control" id="email" name="email" placeholder="E-mail" type="email" required>
        		</div>
      		</div>
      		<div class="row">
      			<div class="col-sm-12 form-group">
      				<textarea class="form-control" id="comments" name="comments" placeholder="Comentários" rows="5"></textarea><br>
      			</div>
      		</div>
      		<div class="row">
        		<div class="col-sm-12 form-group">
          			<button class="btn btn-default pull-right" type="submit">Enviar</button>
        		</div>
      		</div>
    	</form>
    </div>
  </div>
</div>

<!-- Add Google Maps -->
<mtag:mapa/>

<footer class="container-fluid text-center">
  <a href="#myPage" title="To Top">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a>
  <p>Siga-nos nas redes sociais</p>
  <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <ul class="list-inline text-center">
                        <li>
                            <a href="http://facebook.com/compliancesoftwarebr">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                      </ul>
                    <p class="copyright text-muted">Copyright &copy; 
					<span lang="pt-br">Compliance Software</span> 2016</p>
                </div>
            </div>
        </div>
</footer>

<mtag:cssJsFoot/>

</body>
</html>
