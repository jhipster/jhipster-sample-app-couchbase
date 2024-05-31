import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: '303c6166-b08e-476f-8c3c-9a6b8943a087',
  login: 'cWhSNA@H3qsvm\\RxU\\.ONVruR',
};

export const sampleWithPartialData: IUser = {
  id: 'c0860af0-0091-4677-8edb-e61da1f7dd52',
  login: 'e',
};

export const sampleWithFullData: IUser = {
  id: '7a0d6b74-86a5-47b8-9ed0-8d59cd468e69',
  login: '4rqPLM@sL\\wg0mU\\YBc',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
