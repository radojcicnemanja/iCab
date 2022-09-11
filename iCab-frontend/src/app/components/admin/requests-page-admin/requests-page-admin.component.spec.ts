import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestsPageAdminComponent } from './requests-page-admin.component';

describe('RequestsPageAdminComponent', () => {
  let component: RequestsPageAdminComponent;
  let fixture: ComponentFixture<RequestsPageAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestsPageAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestsPageAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
