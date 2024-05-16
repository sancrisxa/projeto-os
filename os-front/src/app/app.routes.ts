import { Routes } from '@angular/router';
import { HomeComponent } from './views/components/home/home.component';
import { TecnicoReadComponent } from './views/components/tecnico/tecnico-read/tecnico-read.component';
import { TecnicoCreateComponent } from './views/components/tecnico/tecnico-create/tecnico-create.component';
import { TecnicoUpdateComponent } from './views/components/tecnico/tecnico-update/tecnico-update.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'tecnicos',
        component: TecnicoReadComponent
    },
    {
        path: 'tecnicos/create',
        component: TecnicoCreateComponent
    },
    {
        path: 'tecnicos/update/:id',
        component: TecnicoUpdateComponent
    }
];
