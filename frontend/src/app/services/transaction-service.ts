import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { TransactionModel } from '../models/transaction';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  
  url = 'http://localhost:8080/transactions';

  http = inject(HttpClient);

  getAllTransactions() {
    return this.http.get<TransactionModel[]>(this.url);
  }

  addTransaction(transaction: TransactionModel) {
    return this.http.post(this.url, transaction, {responseType: 'text'});
  }
}
