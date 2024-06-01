import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Router,RouterModule } from '@angular/router';
import { Cliente } from '../../../../models/cliente';
import { ClienteService } from '../../../../services/cliente.service';



@Component({
  selector: 'app-cliente-read',
  standalone: true,
  imports: [ RouterModule, MatPaginatorModule, MatTableModule ],
  templateUrl: './cliente-read.component.html',
  styleUrls: ['./cliente-read.component.css']
})
export class ClienteReadComponent implements AfterViewInit {

  cliente: Cliente[] = [];

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'telefone', 'action'];
  dataSource = new MatTableDataSource<Cliente>(this.cliente);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service : ClienteService,
    private router : Router) {}

  ngAfterViewInit() {
    this.findAll();
  }

  findAll():void {
    this.service.findAll().subscribe((resposta) => {
      this.cliente = resposta;
      this.dataSource = new MatTableDataSource<Cliente>(this.cliente);
      this.dataSource.paginator = this.paginator;
    })
  }

  navigateToCreate():void {
    this.router.navigate(['clientes/create'])
  }

}