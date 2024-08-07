import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cliente } from '../model/Cliente';
import { ClienteService } from '../service/cliente.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-principal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css'] 
})
export class PrincipalComponent {
  
  filtroRazaoSocial: string = '';
  filtroCnpj: string = '';

  
  cliente = new Cliente();
  clientes: Cliente[] = [];
  btnCadastro: boolean = true;
  tabela: boolean = true;

  public isSelecionar: boolean = false;

  constructor(
    private service: ClienteService,
    private authService: AuthService, 
    private router: Router 
  ) {}

  ngOnInit() {
    this.listarClientes();
  }

  listarClientes(): void {
    this.service.listarClientes().subscribe(retorno => this.clientes = retorno);
    console.log(this.clientes);
  }

  
  cadastrarClientes(): void {
    let mensagemErro = '';
    switch (true) {
        case !this.cliente.username:
            mensagemErro += 'O campo Nome é obrigatório.\n';
            break;
        case !this.cliente.password:
            mensagemErro += 'O campo Senha é obrigatório.\n';
            break;
        case !this.cliente.cnpj:
            mensagemErro += 'O campo CNPJ é obrigatório.\n';
            break;
        case !this.cliente.razaoSocial:
            mensagemErro += 'O campo Razão Social é obrigatório.\n';
            break;
        case !this.cliente.status:
            mensagemErro += 'O campo Status Social é obrigatório.\n';
            break;
    }
    if (mensagemErro) {
        alert(mensagemErro);
        return;
    }
    
    this.service.cadastrarClientes(this.cliente).subscribe(retorno => {
            this.clientes.push(retorno);
            this.cliente = new Cliente(); 
            alert("Cliente cadastrado com Sucesso!");
            this.listarClientes(); 
    });
}

  
  selecionarCliente(posicao: number): void {
    this.isSelecionar = true;
    this.cliente = this.clientes[posicao];
    this.btnCadastro = false;
    this.tabela = false;
  }

  
  editarCliente(): void {
    if (!this.cliente.username) {
      alert("Todos os campos são obrigatórios!");
      return;
    }
    this.service.editarClientes(this.cliente).subscribe(retorno => {
      let posicao = this.clientes.findIndex(obj => obj.id == retorno.id);
      this.clientes[posicao] = retorno;
      this.cliente = new Cliente();
      this.btnCadastro = true;
      this.tabela = true;
      this.listarClientes(); 
      alert("Cliente alterado com Sucesso!");
    });
  }

  
  removerCliente(): void {
    this.service.removerClientes(this.cliente.id).subscribe(() => {
      let posicao = this.clientes.findIndex(obj => obj.id == this.cliente.id);
      this.clientes.splice(posicao, 1);
      this.cliente = new Cliente();
      this.btnCadastro = true;
      this.tabela = true;
      this.listarClientes();
      alert("Cliente Removido com Sucesso!");
    });
  }


  cancelarAcao(): void {
    this.cliente = new Cliente();
    this.btnCadastro = true;
    this.tabela = true;
    this.listarClientes();
  }

  
  buscarPorRazaoSocial(): void {
    if (!this.filtroRazaoSocial) {
      alert('Por favor, insira uma razão social para buscar.');
      return;
    }
    
    this.service.buscarPorRazaoSocial(this.filtroRazaoSocial).subscribe(retorno => {
      this.clientes = retorno;
    });
  }

  
  buscarPorCnpj(): void {
    if (!this.filtroCnpj) {
      alert('Por favor, insira um CNPJ para buscar.');
      return;
    }
    
    this.service.buscarPorCnpj(this.filtroCnpj).subscribe(retorno => {
      this.clientes = retorno;
    });
  }

  
  logout(): void {
    this.authService.logout(); 
    this.router.navigate(['/login']); 
  }
}
