export interface UserModel{

    username: string,
    email: string,
    roles: Array<Role>
    roleName: string
}
interface Role {
    roleName: string;
  }