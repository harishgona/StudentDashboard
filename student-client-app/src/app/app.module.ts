import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent }  from './app.component';
import { StudentComponent }  from './student.component';
import { StudentService } from './student.service';

@NgModule({
  imports: [     
        BrowserModule,
		HttpModule,
		ReactiveFormsModule
  ],
  declarations: [
        AppComponent,
		StudentComponent
  ],
  providers: [
        StudentService
  ],
  bootstrap: [
        AppComponent
  ]
})
export class AppModule { }
