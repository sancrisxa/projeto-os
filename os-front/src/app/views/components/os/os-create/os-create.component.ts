import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from '../../../../models/cliente';
import { OS } from '../../../../models/os';
import { Tecnico } from '../../../../models/tecnico';
import { ClienteService } from '../../../../services/cliente.service';
import { OsService } from '../../../../services/os.service';
import { TecnicoService } from '../../../../services/tecnico.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule }   from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { RouterModule } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';


@Component({
  selector: 'app-os-create',
  standalone: true,
  imports: [ MatSelectModule, RouterModule, MatPaginatorModule, MatTableModule, MatInputModule, ReactiveFormsModule, MatFormFieldModule, FormsModule, CommonModule ],
  templateUrl: './os-create.component.html',
  styleUrls: ['./os-create.component.css']
})
export class OsCreateComponent implements OnInit {

  os: OS = {
    tecnico: '',
    cliente: '',
    observacoes: '',
    status: '',
    prioridade: ''
  }

  tecnicos: Tecnico[] = []
  clientes: Cliente[] = []

  constructor(
    private tecnicoService: TecnicoService,
    private clienteService: ClienteService,
    private service: OsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.listarTecnicos();
    this.listarClientes();
  }

  create(): void {
    this.service.create(this.os).subscribe(resposta => {
      this.service.message("Ordem de Serviço criada com sucesso!");
      this.router.navigate(['os'])
    })
  }

  cancel():void {
    this.router.navigate(['os'])
  }

  listarTecnicos():void {
    this.tecnicoService.findAll().subscribe(resposta => {
      this.tecnicos = resposta;
    })
  }

  listarClientes(): void {
    this.clienteService.findAll().subscribe(resposta => {
      this.clientes = resposta;
    })
  }
}