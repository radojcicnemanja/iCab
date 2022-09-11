import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersPageAdminComponent } from './users-page-admin.component';

describe('UsersPageAdminComponent', () => {
  let component: UsersPageAdminComponent;
  let fixture: ComponentFixture<UsersPageAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersPageAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersPageAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
