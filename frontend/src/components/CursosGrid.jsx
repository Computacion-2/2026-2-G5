import CursosCard from "./CursosCard";

function CursosGrid ({ cursos, onSeleccionarCurso }) {
    if(!cursos || cursos.length === 0) {
        return <p className="card-subtitle">No hay cursos disponibles</p>
    }

    return (
        <div className="grid-cursos">
            {cursos.map ((c, index) => (
                <CursosCard key={c.id} curso={c} destacado={index === 0} onSeleccionar={onSeleccionarCurso}/>
            ))}
        </div>
    );
}

export default CursosGrid;