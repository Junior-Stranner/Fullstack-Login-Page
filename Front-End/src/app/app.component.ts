// Importa o decorador 'Component' de '@angular/core', que é usado para definir componentes Angular.
import { Component } from '@angular/core';
// Importa 'RouterOutlet' de '@angular/router', que é usado para renderizar componentes quando a rota corresponde a um caminho específico.
import { RouterOutlet } from '@angular/router';
// Importa o componente 'HomeComponent' do arquivo './components/home/home.component', que será usado posteriormente.
import { HomeComponent } from './components/home/home.component';
// Define um novo componente Angular com o seletor 'app-root'.
@Component({
  selector: 'app-root',
  // Define 'standalone' como true, o que não é um atributo padrão em Angular. 
  // Normalmente, não faz parte da configuração de componentes Angular.
  standalone: true,
  // Importa o 'RouterOutlet', que não é um módulo Angular. 
  // Normalmente, os módulos são importados no arquivo do módulo, não no componente.
  imports: [RouterOutlet],
  // Define o template do componente, que será renderizado no lugar onde este componente for utilizado.
  templateUrl: './app.component.html',
  // Define o estilo do componente a partir do arquivo './app.component.scss'.
  styleUrl: './app.component.scss'
})
// Define a classe AppComponent, que representa a lógica do componente.
export class AppComponent {
  // Define uma propriedade 'title' que armazena o título do aplicativo.
  title = 'landing-page';
}
