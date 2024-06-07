import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from '../../../../models/cliente';
import { ClienteService } from '../../../../services/cliente.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule }   from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-cliente-delete',
  standalone: true,
  imports: [ MatInputModule, ReactiveFormsModule, MatFormFieldModule, FormsModule, CommonModule ],
  templateUrl: './cliente-delete.component.html',
  styleUrls: ['./cliente-delete.component.css']
})
export class ClienteDeleteComponent implements OnInit {

  id_tec = ''

  cliente: Cliente = {
    id: '',
    nome: '',
    cpf: '',
    telefone: ''
  }

  constructor(
    private router: Router,
    private service: ClienteService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id_tec = this.route.snapshot.paramMap.get('id')!
    this.findById();
  }

  findById(): void {
    this.service.findById(this.id_tec).subscribe(resposta => {
      this.cliente = resposta;
    })
  }

  delete(): void {
    this.service.delete(this.id_tec).subscribe(resposta => {
      this.router.navigate(['clientes'])
      this.service.message('Técnico deletado com sucesso!')
    }, err => {
      if (err.error.error.match('possui Ordens de Serviço')) {
        this.service.message(err.error.error);
      }
    })
  }

  cancel(): void {
    this.router.navigate(['clientes'])
  }


}