import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

import { ClientesService } from '../../clientes.service';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-clientes-list',
  templateUrl: './clientes-list.component.html',
  styleUrls: ['./clientes-list.component.css']
})
export class ClientesListComponent implements OnInit {

  clientes: Cliente[] = [];
  clienteSelecionado!: Cliente;
  mensagemSucesso?: String;
  mensagemErro?: String;

  constructor(
    private service: ClientesService,
    private router: Router ) { }

  ngOnInit(): void {
    this.service
    .getClientes()
    .subscribe( resposta => this.clientes = resposta );
  }

  novoCadastro(){
    this.router.navigate(['/clientes/form'])
  }

  preparaDelete(cliente: Cliente){
    this.clienteSelecionado = cliente;
  }

  deletarCliente(){
    this.service
    .deletar(this.clienteSelecionado)
    .subscribe(
      response => {
        this.mensagemSucesso = 'Cliente removido com sucesso.'
        this.ngOnInit();
      },
      erro => this.mensagemErro = 'Erro ao tentar remover o cliente.'
    )
  }
}
