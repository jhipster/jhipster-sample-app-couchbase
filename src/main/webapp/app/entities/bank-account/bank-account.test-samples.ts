import { IBankAccount, NewBankAccount } from './bank-account.model';

export const sampleWithRequiredData: IBankAccount = {
  id: 'ed8c4e9d-ca66-4775-b044-eef9cbed9202',
  name: 'onto extremely simplistic',
  balance: 30602.64,
};

export const sampleWithPartialData: IBankAccount = {
  id: 'd2b585df-112d-44dd-94cb-669b33da0cbc',
  name: 'founder unlined',
  balance: 10951.97,
};

export const sampleWithFullData: IBankAccount = {
  id: '78713b66-085b-487d-b2d0-c57f88e1d10f',
  name: 'seafood',
  balance: 20398.9,
};

export const sampleWithNewData: NewBankAccount = {
  name: 'quantify',
  balance: 29977.55,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
