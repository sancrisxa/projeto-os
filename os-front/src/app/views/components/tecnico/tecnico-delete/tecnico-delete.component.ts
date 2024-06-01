import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tecnico } from '../../../../models/tecnico';
import { TecnicoService } from '../../../../services/tecnico.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-tecnico-delete',
  standalone: true,
  imports: [ FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule ],
  templateUrl: './tecnico-delete.component.html',
  styleUrls: ['./tecnico-delete.component.css']
})
export class TecnicoDeleteComponent implements OnInit {

  id_tec = ''

  tecnico: Tecnico = {
    id: '',
    nome: '',
    cpf: '',
    telefone: ''
  }

  constructor(
    private router: Router,
    private service: TecnicoService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id_tec = this.route.snapshot.paramMap.get('id')!
    this.findById();
  }

  findById(): void {
    this.service.findById(this.id_tec).subscribe(resposta => {
      this.tecnico = resposta;
    })
  }

  delete(): void {
    this.service.delete(this.id_tec).subscribe(resposta => {
      this.router.navigate(['tecnicos'])
      this.service.message('Técnico deletado com sucesso!')
    }, err => {
      if (err.error.error.match('possui Ordens de Serviço')) {
        this.service.message(err.error.error);
      }
    })
  }

  cancel(): void {
    this.router.navigate(['tecnicos'])
  }


}