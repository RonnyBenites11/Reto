import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FutbolistaComponent } from './pages/futbolista/futbolista.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  {path:'',redirectTo: '/inicio',pathMatch:'full'},
  {path: 'inicio',component:FutbolistaComponent},
  {path: 'iniciar-sesion', component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

