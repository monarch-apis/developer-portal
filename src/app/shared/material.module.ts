import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MdInputModule, MdToolbarModule, MdListModule, MdButtonModule, MdCheckboxModule,
  MdSidenavModule, MdTabsModule, MdIconRegistry, MdIconModule, MdCardModule
} from '@angular/material';

@NgModule({
  declarations: [
  ],
  exports: [
    BrowserAnimationsModule,
    MdInputModule,
    MdToolbarModule,
    MdListModule,
    MdButtonModule,
    MdCheckboxModule,
    MdSidenavModule,
    MdTabsModule,
    MdIconModule,
    MdCardModule
  ],
  providers: [
    MdIconRegistry
  ],
  bootstrap: [
  ]
})
export class MaterialDesignModule { }
