import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Smer } from 'src/app/models/smer';
import { SmerService } from 'src/app/services/smer.service';
import { ProjekatDialogComponent } from '../dialogs/projekat-dialog/projekat-dialog.component';
import { SmerDialogComponent } from '../dialogs/smer-dialog/smer-dialog.component';

@Component({
  selector: 'app-smer',
  templateUrl: './smer.component.html',
  styleUrls: ['./smer.component.css']
})
export class SmerComponent implements OnInit {

  displayedColumns = ['id','naziv','oznaka','actions'];
  dataSource: MatTableDataSource<Smer>;
  subscription: Subscription;

  constructor(private smerService: SmerService,
      private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadData()
  }

  ngOnDestroy():void{
    this.subscription.unsubscribe();
  }

  public loadData(){
    this.subscription = this.smerService.getAllSmer().subscribe(
      data =>{
        this.dataSource = new MatTableDataSource(data)
      }
    ),
    (error:Error)=>{
      console.log(error.name+' '+ error.message)
    }
  }

  public openDialog(flag:number,id?:number,naziv?:string,oznaka?:string):void{
    const dialogRef = this.dialog.open(SmerDialogComponent,{data:{id,naziv,oznaka}});

    dialogRef.componentInstance.flag = flag;

    dialogRef.afterClosed().subscribe(result=>{
      if (result === 1) {
        this.loadData();
      }
    })
  }

}
