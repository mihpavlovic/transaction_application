import { Component, inject, OnInit } from '@angular/core';
import { TransactionModel } from '../../models/transaction';
import { TransactionService } from '../../services/transaction-service';

@Component({
  selector: 'app-transaction-component',
  imports: [],
  templateUrl: './transaction-component.html',
  styleUrl: './transaction-component.css',
})
export class TransactionComponent implements OnInit{

  allTransactions: TransactionModel[] = [];

  private transactionService = inject(TransactionService);

  ngOnInit(): void {
    this.transactionService.getAllTransactions().subscribe(
      data => {
        this.allTransactions = data;
        console.log(this.allTransactions[0].accountHolderName);
      }
    )
  }

}
