import CursosCard from "./CursosCard";

function CursosGrid ({ cursos }) {
    if(!cursos || cursos.length === 0) {
        return <p className="card-subtitle">No hay cursos disponibles</p>
    }

    return (
        <div className="grid-cursos">
            {cursos.map ((c) => (
                <CursosCard key={c.id} curso={c}/>
            ))}
        </div>
    );
}

export default CursosGrid;