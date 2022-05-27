import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Cliente } from '../cliente';
import { ClientesService } from '../../clientes.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  clientes: Cliente[] = [];
  success: boolean = false;
  errors?: String[];
  id?: number;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
      this.cliente = new Cliente();
   }

  ngOnInit(): void {
    this.service
    .getClientes()
    .subscribe( resposta => this.clientes = resposta );

    let params : Observable<any> = this.activatedRoute.params
    params.subscribe(urlParams => this.id = urlParams['id']);
    if(this.id){
      this.service
      .getClientesById(this.id)
      .subscribe(response => this.cliente = response,
        errorResponse => this.cliente = new Cliente())
    }
  }

  onSubmit(){
    if(this.id){
      this.service
      .atualizar(this.cliente)
      .subscribe( response => {
        this.success = true;
      }, errorResponse =>{
        this.errors = ['Erro ao atualizar o cliente'];
      }
      )
    }else{
      this.service
      .salvar(this.cliente)
      .subscribe( response => {
        console.log(response)
        this.success = true;
        this.cliente = response;
      }, errorResponse =>{
        this.success = false;
        this.errors = errorResponse.error.errors;
      }
      )
    }
  }

  voltarListaCliente(){
    this.router.navigate(['/clientes/list'])
  }

}
