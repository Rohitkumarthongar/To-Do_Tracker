import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchieveListComponent } from './archieve-list.component';

describe('ArchieveListComponent', () => {
  let component: ArchieveListComponent;
  let fixture: ComponentFixture<ArchieveListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArchieveListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArchieveListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
