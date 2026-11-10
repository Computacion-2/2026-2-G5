import './App.css'
import CursosCard from './components/CursosCard'
import CursosGrid from './components/CursosGrid'
import Navbar from './components/Navbar'
import { MOCKDATA } from './data/MockData'

function App() {
  return (
    <div>
        <Navbar />
        <main className='container'>
          <h1>Catalogo de cursos</h1>
          {/* <CursosCard curso={{nombre: 'Compunet 1', creditos: 3, departamento:'tic', profesor:'Alejandro'}} /> */}
          <CursosGrid cursos={MOCKDATA} />
        </main>
    </div>
  )
}

export default App
