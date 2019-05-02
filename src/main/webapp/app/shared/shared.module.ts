import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {
  JhipsterCouchbaseSampleApplicationSharedLibsModule,
  JhipsterCouchbaseSampleApplicationSharedCommonModule,
  JhiLoginModalComponent,
  HasAnyAuthorityDirective
} from './';

@NgModule({
  imports: [JhipsterCouchbaseSampleApplicationSharedLibsModule, JhipsterCouchbaseSampleApplicationSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [JhipsterCouchbaseSampleApplicationSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCouchbaseSampleApplicationSharedModule {
  static forRoot() {
    return {
      ngModule: JhipsterCouchbaseSampleApplicationSharedModule
    };
  }
}
