import Navbar from "./components/Navbar";
import {Outlet} from 'react-router';

export default function Root() {
    return(
        <div>
            <Navbar/>
            <main className="container">
                <Outlet />
            </main>
        </div>
    )
}