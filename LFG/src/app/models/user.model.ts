import { Group } from "./group.model"

export class User {

    private firstName: string = "";
    private lastName: string = ""; 
    private password: string = "";
    
    private stats: string = "";
    
    private appCount: number = 0;
    private inGroup: boolean = false;
    
    private group!: Group;
    
    constructor(private id: number,private username: string, private email: string){
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public get _id(): number {
        return this.id;
    }
    public set _id(value: number) {
        this.id = value;
    }


    public get _username(): string {
        return this.username;
    }
    public set _username(value: string) {
        this.username = value;
    }


    public get _firstName(): string {
        return this.firstName;
    }
    public set _firstName(value: string) {
        this.firstName = value;
    }


    public get _lastName(): string {
        return this.lastName;
    }
    public set _lastName(value: string) {
        this.lastName = value;
    }

    public get _password(): string {
        return this.password;
    }
    public set _password(value: string) {
        this.password = value;
    }
    
    public get _email(): string {
        return this.email;
    }
    public set _email(value: string) {
        this.email = value;
    }


    public get _stats(): string {
        return this.stats;
    }
    public set _stats(value: string) {
        this.stats = value;
    }


    get _appCount() {
        return this.appCount;
    }
    set _appCount(value: number) {
        this.appCount = value;
    }

    resetAppCount(): void {
        this.appCount = 0;
    }
    incrementAppCount(): void {
        this.appCount++;
    }
    decrementAppCount(): void {
        this.appCount--;
    }


    public get _inGroup(): boolean {
        return this.inGroup;
    }
    public set _inGroup(value: boolean) {
        this.inGroup = value;
    }

    
    public get _group(): Group {
        return this.group;
    }
    public set _group(value: Group) {
        this.group = value;
    }
}
